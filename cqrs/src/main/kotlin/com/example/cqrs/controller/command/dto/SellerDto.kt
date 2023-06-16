package com.example.cqrs.controller.command.dto

data class CreateSellerRequest(
    val email: String,
    val name: String
)

data class UpdateSellerRequest(
    val sellerNo: Long,
    val email: String,
    val name: String
)

data class SellerResponse(
    val sellerNo: Long,
    val email: String,
    val name: String
)