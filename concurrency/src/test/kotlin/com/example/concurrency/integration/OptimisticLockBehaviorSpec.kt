package com.example.concurrency.integration

import com.example.concurrency.exception.*
import com.example.concurrency.repository.*
import com.example.concurrency.service.*
import io.kotest.core.spec.style.*
import io.kotest.matchers.*
import org.slf4j.*
import org.springframework.boot.test.context.*
import org.springframework.dao.*
import org.springframework.data.repository.*
import org.springframework.test.context.*
import java.util.concurrent.*

@SpringBootTest
@TestPropertySource(properties = ["spring.profiles.active = test"])
class OptimisticLockBehaviorSpec(
    private val itemService: ItemService,
    private val optimisticLockItemRepository: OptimisticLockItemRepository
) : BehaviorSpec({
    val log = LoggerFactory.getLogger(javaClass)
    var exceptionCount = 0

    afterTest {
        optimisticLockItemRepository.deleteAll()
        exceptionCount = 0
    }

    Given("하나의 상품이 50개의 재고가 있을 때") {
        val itemNo = itemService.saveOptimisticLockItem("item1", 50)

        When("20명이 동시에 주문하는 경우") {
            val threadPool = Executors.newFixedThreadPool(32)
            val latch = CountDownLatch(20)

            for (i: Int in 1 .. 20) {
                threadPool.submit {
                    while (true) {
                        try {
                            /** Optimistic Lock */
                            itemService.minusItemQuantityWithOptimisticLock(itemNo)
                            break
                        } catch (e: OptimisticLockingFailureException) {
                            exceptionCount++
                            log.warn("OptimisticLockingFailureException! exceptionCount = {}, message = {}", exceptionCount, e.message)
                            Thread.sleep(50)
                        }
                    }
                    latch.countDown()
                }
            }

            latch.await()

            Then("상품의 재고는 30이 된다.") {
                val item = optimisticLockItemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()
                val isGreaterThanZero = exceptionCount > 0

                isGreaterThanZero shouldBe true
                item.itemQuantity shouldBe 30
            }
        }
    }
})