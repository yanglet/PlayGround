package com.example.cloudstream.event

data class PayEvent(
    val memberNo: Long
)

data class ItemMinusQuantityEvent(
    val itemNo: Long,
    val quantity: Long
)