package com.example.cloudstream.kafka

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

const val BINDING_NAME = "create-order"

@Component
class EventProducer(
    private val streamBridge: StreamBridge
) {

    fun send(message: Message<Any>) {
        streamBridge.send(
            BINDING_NAME,
            MessageBuilder
                .withPayload(message.payLoad)
//                .setHeader()
                .build()
        )
    }
}