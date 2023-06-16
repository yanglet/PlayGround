package com.example.cqrs.controller.command.dto

import java.math.BigDecimal

data class CreateHotDealItemRequest(
    val itemName: String,
    val itemPrice: BigDecimal,
    val sellerNo: Long,
    val categoryNo: Long
)

data class UpdateHotDealItemRequest(
    val itemName: String?,
    val itemPrice: BigDecimal?
)

data class HotDealItemResponse(
    val hotDealItemNo: Long,
    val itemName: String,
    val itemPrice: BigDecimal,
    val seller: SellerResponse,
    val category: CategoryResponse
)