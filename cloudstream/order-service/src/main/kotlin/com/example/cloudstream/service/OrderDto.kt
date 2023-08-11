package com.example.cloudstream.service

import java.time.LocalDateTime

data class OrderResponse(
    val orderNo: Long,
    val itemNo: Long,
    val memberNo: Long,
    val insertDate: LocalDateTime
)