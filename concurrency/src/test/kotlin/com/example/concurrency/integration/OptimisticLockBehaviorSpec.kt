package com.example.concurrency.integration

import com.example.concurrency.exception.ItemNotFoundException
import com.example.concurrency.repository.ItemRepository
import com.example.concurrency.service.ItemService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.TestPropertySource
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@TestPropertySource(properties = ["spring.profiles.active = test"])
class OptimisticLockBehaviorSpec(
    private val itemService: ItemService,
    private val itemRepository: ItemRepository
) : BehaviorSpec({
    val log = LoggerFactory.getLogger(javaClass)
    var exceptionCount = 0

    afterTest {
        itemRepository.deleteAll()
        exceptionCount = 0
    }

    Given("하나의 상품이 50개의 재고가 있을 때") {
        val itemNo = itemService.saveItem("item1", 50)

        When("20명이 동시에 주문하는 경우") {
            val threadPool = Executors.newFixedThreadPool(32)
            val latch = CountDownLatch(20)

            for (i: Int in 1 .. 20) {
                threadPool.submit {
                    try {
                        /** Optimistic Lock */
                        itemService.minusItemQuantityWithOptimisticLock(itemNo)
                    } catch (e: OptimisticLockingFailureException) {
                        exceptionCount++
                        log.warn("OptimisticLockingFailureException! exceptionCount = {}, message = {}", exceptionCount, e.message)
                    } finally {
                        latch.countDown()
                    }
                }
            }

            latch.await()

            Then("상품의 재고는 30이 될 수 없다.") {
                val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()
                val isGreaterThanZero = exceptionCount > 0

                isGreaterThanZero shouldBe true
                item.itemQuantity shouldNotBe 30
            }
        }
    }
})