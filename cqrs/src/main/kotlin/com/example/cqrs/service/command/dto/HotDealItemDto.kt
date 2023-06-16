package com.example.cqrs.service.command.dto

import java.math.BigDecimal

data class CreateHotDealItemParam(
    val itemName: String,
    val itemPrice: BigDecimal,
    val sellerNo: Long,
    val categoryNo: Long
)

data class UpdateHotDealItemParam(
    val hotDealItemNo: Long,
    val itemName: String?,
    val itemPrice: BigDecimal?
)

data class HotDealItemResult(
    val hotDealItemNo: Long,
    val itemName: String,
    val itemPrice: BigDecimal,
    val seller: SellerResult,
    val category: CategoryResult
)