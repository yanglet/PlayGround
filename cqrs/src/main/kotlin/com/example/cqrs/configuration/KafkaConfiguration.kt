package com.example.cqrs.configuration

import org.apache.kafka.clients.consumer.*
import org.apache.kafka.clients.producer.*
import org.apache.kafka.common.serialization.*
import org.springframework.context.annotation.*
import org.springframework.kafka.annotation.*
import org.springframework.kafka.config.*
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.*


@Configuration
@EnableKafka
class KafkaConfiguration {
    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(producerConfigs())
    }

    @Bean
    fun producerConfigs(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9094"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return props
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.setConsumerFactory(consumerFactory())
        factory.setConcurrency(3)
        factory.containerProperties.pollTimeout = 3000
        return factory
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        return DefaultKafkaConsumerFactory(consumerConfigs())
    }

    @Bean
    fun consumerConfigs(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9094"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return props
    }
}