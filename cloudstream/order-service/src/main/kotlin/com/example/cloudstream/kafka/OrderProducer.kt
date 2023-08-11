package com.example.cloudstream.kafka

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderProducer(
    private val streamBridge: StreamBridge
) {
    fun send(message: Message<Any>, bindingName: String) {
        streamBridge.send(
            bindingName,
            MessageBuilder
                .withPayload(message.payLoad)
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
                .build()
        )
    }
}