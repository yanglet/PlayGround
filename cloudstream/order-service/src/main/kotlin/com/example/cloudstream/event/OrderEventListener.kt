package com.example.cloudstream.event

import com.example.cloudstream.kafka.EventProducer
import com.example.cloudstream.kafka.Message
import org.hibernate.internal.util.beans.BeanInfoHelper.BeanInfoDelegate
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

const val BINDING_NAME = "order-create"

@Component
class OrderEventListener(
    private val eventProducer: EventProducer
) {

    @EventListener
    fun payEventListener(event: PayEvent) {
        eventProducer.send(Message(event), BINDING_NAME)
    }

    @EventListener
    fun itemMinusQuantityListener(event: ItemMinusQuantityEvent) {
        eventProducer.send(Message(event), BINDING_NAME)
    }
}