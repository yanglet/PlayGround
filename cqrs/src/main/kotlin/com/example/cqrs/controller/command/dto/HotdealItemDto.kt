package com.example.cqrs.controller.command.dto

import java.math.BigDecimal

data class CreateHotdealItemRequest(
    val itemName: String,
    val itemPrice: BigDecimal,
    val sellerNo: Long,
    val categoryNo: Long
)

data class UpdateHotdealItemRequest(
    val itemName: String?,
    val itemPrice: BigDecimal?
)

data class HotdealItemResponse(
    val hotdealItemNo: Long,
    val itemName: String,
    val itemPrice: BigDecimal,
    val seller: SellerResponse,
    val category: CategoryResponse
)