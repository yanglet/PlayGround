package com.example.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CoroutineTest {
    val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun dispatchersIOTest() {
        val begin = System.currentTimeMillis()

        runBlocking(Dispatchers.IO) {
            for (i in 1..20) {
                launch { wait() }
            }
        }

        val end = System.currentTimeMillis()
        val processingTime = end - begin

        log.info("Test processingTime = $processingTime")

        Assertions.assertThat(processingTime).isLessThan(2000)
        Assertions.assertThat(processingTime).isGreaterThan(1000)
    }

    @Test
    fun dispatchersDefaultTest() {
        val begin = System.currentTimeMillis()

        runBlocking(Dispatchers.Default) {
            for (i in 1..20) {
                launch { wait() }
            }
        }

        val end = System.currentTimeMillis()
        val processingTime = end - begin

        log.info("Test processingTime = $processingTime")

        Assertions.assertThat(processingTime).isLessThan(2000)
        Assertions.assertThat(processingTime).isGreaterThan(1000)
    }

    @Test
    fun dispatchersUnconfinedTest() {
        val begin = System.currentTimeMillis()

        runBlocking(Dispatchers.Unconfined) {
            for (i in 1..20) {
                launch { wait() }
            }
        }

        val end = System.currentTimeMillis()
        val processingTime = end - begin

        log.info("Test processingTime = $processingTime")

        Assertions.assertThat(processingTime).isLessThan(2000)
        Assertions.assertThat(processingTime).isGreaterThan(1000)
    }

    suspend fun wait() {
        val begin = System.currentTimeMillis()
        delay(1000)
        val end = System.currentTimeMillis()

        log.info("${Thread.currentThread().name} processingTime = ${end - begin}")
    }
}