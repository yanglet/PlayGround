package com.example.cloudstream.event

data class OrderEvent(
    val memberNo: Long,
    val itemNo: Long,
    val quantity: Long
)