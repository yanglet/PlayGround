package com.example.cqrs.kafka

import org.springframework.kafka.annotation.*
import org.springframework.stereotype.*


@Component
class KafkaConsumer {
    @KafkaListener(
        id = "cqrs",
        topics = ["cqrs_topic"],
        clientIdPrefix = "clientId",
        properties = ["enable.auto.commit:false", "auto.offset.reset:latest"]
    )
    fun listen(data: String) {
        println("Consumed data : $data")
    }
}