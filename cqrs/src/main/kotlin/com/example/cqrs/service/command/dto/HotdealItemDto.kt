package com.example.cqrs.service.command.dto

import java.math.BigDecimal

data class CreateHotdealItemParam(
    val itemName: String,
    val itemPrice: BigDecimal,
    val sellerNo: Long,
    val categoryNo: Long
)

data class UpdateHotdealItemParam(
    val hotDealItemNo: Long,
    val itemName: String?,
    val itemPrice: BigDecimal?
)

data class HotdealItemResult(
    val hotdealItemNo: Long,
    val itemName: String,
    val itemPrice: BigDecimal,
    val seller: SellerResult,
    val category: CategoryResult
)