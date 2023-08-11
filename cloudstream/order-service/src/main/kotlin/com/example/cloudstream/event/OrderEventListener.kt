package com.example.cloudstream.event

import com.example.cloudstream.kafka.EventProducer
import com.example.cloudstream.kafka.Message
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderEventListener(
    private val eventProducer: EventProducer
) {

    @EventListener
    fun payEventListener(event: PayEvent) {
        eventProducer.send(Message(event))
    }

    @EventListener
    fun itemMinusQuantityListener(event: ItemMinusQuantityEvent) {
        eventProducer.send(Message(event))
    }
}