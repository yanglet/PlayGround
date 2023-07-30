package com.example.concurrency.integration

import com.example.concurrency.exception.*
import com.example.concurrency.repository.*
import com.example.concurrency.service.*
import io.kotest.core.spec.style.*
import io.kotest.matchers.*
import org.springframework.boot.test.context.*
import org.springframework.data.repository.*
import org.springframework.test.context.*
import java.util.concurrent.*

@SpringBootTest
@TestPropertySource(properties = ["spring.profiles.active = test"])
class AtomicIntegerBehaviorSpec(
    private val itemService: ItemService,
    private val atomicItemRepository: AtomicItemRepository
) : BehaviorSpec({

    afterTest {
        atomicItemRepository.deleteAll()
    }

    Given("하나의 상품이 50개의 재고가 있을 때") {
        val itemNo = itemService.saveAtomicItem("item1", 50)

        When("20명이 동시에 주문하는 경우") {
            val threadPool = Executors.newFixedThreadPool(20)
            val latch = CountDownLatch(20)

            for (i: Int in 1..20) {
                threadPool.submit {
                    try {
                        /** Atomic Integer */
                        itemService.minusAtomicItemQuantity(itemNo)
                    } finally {
                        latch.countDown()
                    }
                }
            }

            latch.await()

            Then("상품의 재고는 30이 된다.") {
                val item = atomicItemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()
                item.itemQuantity shouldBe 30
            }
        }
    }
})