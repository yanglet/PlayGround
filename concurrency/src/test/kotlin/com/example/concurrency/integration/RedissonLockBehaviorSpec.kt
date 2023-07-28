package com.example.concurrency.integration

import com.example.concurrency.exception.ItemNotFoundException
import com.example.concurrency.repository.ItemRepository
import com.example.concurrency.service.ItemService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.TestPropertySource
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@TestPropertySource(properties = ["spring.profiles.active = test"])
class RedissonLockBehaviorSpec(
    private val itemService: ItemService,
    private val itemRepository: ItemRepository
) : BehaviorSpec({

    afterTest {
        itemRepository.deleteAll()
    }

    Given("하나의 상품이 50개의 재고가 있을 때") {
        val itemNo = itemService.saveItem("item1", 50)

        When("20명이 동시에 주문하는 경우") {
            val threadPool = Executors.newFixedThreadPool(20)
            val latch = CountDownLatch(20)

            for (i: Int in 1 .. 20) {
                threadPool.submit {
                    try {
                        /** Redisson Lock */
                        itemService.minusItemQuantityWithRedissonLock(itemNo)
                    } finally {
                        latch.countDown()
                    }
                }
            }

            latch.await()

            Then("상품의 재고는 30이 된다.") {
                val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()
                item.itemQuantity shouldBe 30
            }
        }
    }
})