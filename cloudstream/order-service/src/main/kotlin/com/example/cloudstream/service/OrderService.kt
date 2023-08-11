package com.example.cloudstream.service

import com.example.cloudstream.entity.Order
import com.example.cloudstream.event.ItemMinusQuantityEvent
import com.example.cloudstream.event.PayEvent
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
    /**
     * 주문 발생
     * 예제니까 그냥 정한대로 만듦.
     * 1. 결제 이벤트 (Spring event) -> 카프카 메세지 발행
     * 2. 상품 재고 차감 이벤트 (Spring event) -> 카프카 메세지 발행
     * 3. 주문 생성 및 완료
     */
    fun order(memberNo: Long, itemNo: Long, orderQuantity: Long): OrderResponse {
        eventPublisher.publishEvent(PayEvent(memberNo))
        eventPublisher.publishEvent(ItemMinusQuantityEvent(itemNo, orderQuantity))

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