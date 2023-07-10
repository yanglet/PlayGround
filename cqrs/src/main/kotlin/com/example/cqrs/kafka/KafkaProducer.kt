package com.example.cqrs.kafka

import org.springframework.kafka.core.*
import org.springframework.stereotype.*


@Component
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    fun send(text: String) {
        kafkaTemplate.send("cqrs-topic", text)
    }
}