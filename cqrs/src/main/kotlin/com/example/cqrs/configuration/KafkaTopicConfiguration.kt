package com.example.cqrs.configuration

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfiguration {
    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs: MutableMap<String, Any?> = HashMap()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = "kafka:9092"
        return KafkaAdmin(configs)
    }

    @Bean
    fun categoryTopic(): NewTopic = NewTopic("db_cqrs_category", 1, 1.toShort())

    @Bean
    fun sellerTopic(): NewTopic = NewTopic("db_cqrs_seller", 1, 1.toShort())

    @Bean
    fun discountTopic(): NewTopic = NewTopic("db_cqrs_discount", 1, 1.toShort())

    @Bean
    fun hotdealItemTopic(): NewTopic = NewTopic("db_cqrs_hotdeal_item", 1, 1.toShort())
}