package com.example.cloudstream.kafka

import com.example.cloudstream.service.*
import org.springframework.context.annotation.*
import org.springframework.messaging.*
import org.springframework.stereotype.*
import java.util.function.*

@Component
class PayConsumer(
    private val payService: PayService
) {
    @Bean
    fun pay(): Consumer<Message<OrderMessage>> {
        return Consumer<Message<OrderMessage>> { message ->
            payService.pay(message.payload.memberNo)
        }
    }
}