package com.example.concurrency.config

import org.redisson.*
import org.redisson.api.*
import org.redisson.config.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissonConfiguration {
    @Value("\${redis.host}")
    var redisHost: String = ""
    @Value("\${redis.port}")
    var redisPort: String = ""

    @Bean
    fun redissonClient(): RedissonClient? {
        val config = Config()
        config.useSingleServer().address = "redis://$redisHost:$redisPort"
        return Redisson.create(config)
    }
}