package com.example.concurrency.service.dto

data class ItemUpdateParam(
    val itemNo: Long,
    val requestCount: Int,
    val type: String
)