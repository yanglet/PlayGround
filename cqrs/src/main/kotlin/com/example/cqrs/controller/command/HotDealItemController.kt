package com.example.cqrs.controller.command

import com.example.cqrs.controller.command.dto.*
import com.example.cqrs.controller.dto.*
import com.example.cqrs.service.command.HotDealItemService
import com.example.cqrs.service.command.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/hotdeal-items")
class HotDealItemController(
    private val hotDealItemService: HotDealItemService
) {
    @PostMapping
    fun createHotDealItem(@RequestBody request: CreateHotDealItemRequest) =
        ResponseEntity.ok(
            hotDealItemService.createHotDealItem(
                request.toParam()
            ).toResponse()
        )

    @PutMapping("/{hotDealItemNo}")
    fun updateHotDealItem(
        @PathVariable hotDealItemNo: Long,
        @RequestBody request: UpdateHotDealItemRequest
    ) = ResponseEntity.ok(
        hotDealItemService.updateHotDealItem(
            request.toParam(hotDealItemNo)
        ).toResponse()
    )

    private fun UpdateHotDealItemRequest.toParam(hotDealItemNo: Long) = UpdateHotDealItemParam(
        hotDealItemNo = hotDealItemNo,
        itemName = this.itemName,
        itemPrice = this.itemPrice
    )

    private fun CreateHotDealItemRequest.toParam() = CreateHotDealItemParam(
        itemName = this.itemName,
        itemPrice = this.itemPrice,
        sellerNo = this.sellerNo,
        categoryNo = this.categoryNo
    )

    private fun HotDealItemResult.toResponse() = HotDealItemResponse(
        hotDealItemNo = this.hotDealItemNo,
        itemName = this.itemName,
        itemPrice = this.itemPrice,
        seller = this.seller.toResponse(),
        category = this.category.toResponse()
    )

    private fun SellerResult.toResponse() = SellerResponse(
        sellerNo = this.sellerNo,
        email = this.email,
        name = this.name
    )

    private fun CategoryResult.toResponse() = CategoryResponse(
        categoryNo = this.categoryNo,
        categoryName = this.categoryName
    )
}