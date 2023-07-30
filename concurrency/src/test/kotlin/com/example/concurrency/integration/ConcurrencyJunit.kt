package com.example.concurrency.integration

import com.example.concurrency.exception.ItemNotFoundException
import com.example.concurrency.repository.ItemRepository
import com.example.concurrency.service.ItemService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.TestPropertySource
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@TestPropertySource(properties = ["spring.profiles.active = test"])
class ConcurrencyJunit {
    @Autowired
    lateinit var itemService: ItemService
    @Autowired
    lateinit var itemRepository: ItemRepository

    @AfterEach
    fun afterEach() {
        itemRepository.deleteAll()
    }

    @Test
    fun `20명이 동시 주문시 상품 재고 테스트`() {
        val itemNo = itemService.saveItem("item1", 30)
        val threadPool = Executors.newFixedThreadPool(20)
        val latch = CountDownLatch(20)

        for (i: Int in 1..20) {
            threadPool.execute {
                try {
                    itemService.minusItemQuantity(itemNo)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()

        assertThat(item.itemQuantity).isNotEqualTo(10L)
    }
}