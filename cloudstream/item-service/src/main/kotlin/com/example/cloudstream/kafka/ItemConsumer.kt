package com.example.cloudstream.kafka

import com.example.cloudstream.service.*
import org.springframework.context.annotation.*
import org.springframework.messaging.*
import org.springframework.stereotype.*
import java.util.function.*

@Component
class ItemConsumer(
    private val itemService: ItemService
) {
    @Bean
    fun minusQuantity(): Consumer<Message<OrderMessage>> {
        return Consumer<Message<OrderMessage>> { message ->
            itemService.minusQuantity(
                message.payload.itemNo,
                message.payload.quantity
            )
        }
    }
}