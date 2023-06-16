package com.example.cqrs.service.command.dto

data class CreateSellerParam(
    val email: String,
    val name: String
)

data class UpdateSellerParam(
    val sellerNo: Long,
    val email: String,
    val name: String
)

data class SellerResult(
    val sellerNo: Long,
    val email: String,
    val name: String
)