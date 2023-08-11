package com.example.cloudstream.kafka

data class OrderMessage(
    val memberNo: Long,
    val itemNo: Long,
    val quantity: Long
)