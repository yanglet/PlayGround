package com.example.cloudstream.service

import com.example.cloudstream.entity.Order
import com.example.cloudstream.event.*
import com.example.cloudstream.repository.OrderRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(
    private val orderRepository: OrderRepository,
    private val eventPublisher: ApplicationEventPublisher
) {
    fun order(memberNo: Long, itemNo: Long, orderQuantity: Long): OrderResponse {
        eventPublisher.publishEvent(OrderEvent(memberNo, itemNo, orderQuantity))

        val order = orderRepository.save(Order(itemNo = itemNo, memberNo = memberNo))

        return order.toResponse()
    }

    private fun Order.toResponse() = OrderResponse(
        orderNo = this.orderNo,
        itemNo = this.itemNo,
        memberNo = this.memberNo,
        insertDate = this.insertDate
    )
}