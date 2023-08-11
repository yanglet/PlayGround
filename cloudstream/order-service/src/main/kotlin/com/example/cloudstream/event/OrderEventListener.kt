package com.example.cloudstream.event

import com.example.cloudstream.kafka.OrderProducer
import com.example.cloudstream.kafka.Message
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

const val BINDING_NAME = "order-create"

@Component
class OrderEventListener(
    private val producer: OrderProducer
) {
    @EventListener
    fun orderEventListener(event: OrderEvent) {
        producer.send(Message(event), BINDING_NAME)
    }
}