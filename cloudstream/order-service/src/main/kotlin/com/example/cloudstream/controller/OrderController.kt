package com.example.cloudstream.controller

import com.example.cloudstream.service.OrderRequest
import com.example.cloudstream.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/orders")
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping
    fun order(@RequestBody request: OrderRequest) =
        ResponseEntity.ok(
            orderService.order(request.memberNo, request.itemNo, request.orderQuantity)
        )
}