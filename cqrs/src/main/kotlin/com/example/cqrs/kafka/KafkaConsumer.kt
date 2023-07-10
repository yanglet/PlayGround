package com.example.cqrs.kafka

import org.springframework.kafka.annotation.*
import org.springframework.stereotype.*


@Component
class KafkaConsumer {
    @KafkaListener(
        topics = ["cqrs-topic"],
        groupId = "cqrs"
    )
    fun listen(data: String) {
        println("Consumed data : $data")
    }
}