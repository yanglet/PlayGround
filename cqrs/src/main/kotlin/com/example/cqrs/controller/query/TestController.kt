package com.example.cqrs.controller.query

import com.example.cqrs.kafka.KafkaConsumer
import com.example.cqrs.kafka.KafkaProducer
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/v1/tests")
class TestController(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val kafkaConsumer: KafkaConsumer,
    private val kafkaProducer: KafkaProducer
) {
    companion object {
        const val KEY = "REDIS_TEST"
        const val TTL = 365L
    }

    @GetMapping("redis")
    fun getTestRedis(): String? {
        return redisTemplate.opsForValue().get(KEY) as String?
    }

    @PostMapping("redis")
    fun postTestRedis(@RequestParam param: String) {
        redisTemplate.opsForValue().set(KEY, param, TTL, TimeUnit.DAYS)
    }

//    @GetMapping("kafka")
//    fun consumerTest() {
//    }

    @GetMapping("kafka")
    fun producerTest(@RequestParam param: String) {
        kafkaProducer.send(param)
    }
}