package com.example.cqrs.controller.command

import com.example.cqrs.controller.command.dto.*
import com.example.cqrs.service.command.HotdealItemService
import com.example.cqrs.service.command.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/hotdeal-items")
class HotdealItemController(
    private val hotDealItemService: HotdealItemService
) {
    @GetMapping
    fun getHotdealItems() =
        ResponseEntity.ok(
            hotDealItemService.getHotdealItems().map { it.toResponse() }
        )

    @PostMapping
    fun createHotdealItem(@RequestBody request: CreateHotdealItemRequest) =
        ResponseEntity.ok(
            hotDealItemService.createHotdealItem(
                request.toParam()
            ).toResponse()
        )

    @PutMapping("/{hotdealItemNo}")
    fun updateHotdealItem(
        @PathVariable hotdealItemNo: Long,
        @RequestBody request: UpdateHotdealItemRequest
    ) = ResponseEntity.ok(
        hotDealItemService.updateHotdealItem(
            request.toParam(hotdealItemNo)
        ).toResponse()
    )

    private fun UpdateHotdealItemRequest.toParam(hotdealItemNo: Long) = UpdateHotdealItemParam(
        hotDealItemNo = hotdealItemNo,
        itemName = this.itemName,
        itemPrice = this.itemPrice
    )

    private fun CreateHotdealItemRequest.toParam() = CreateHotdealItemParam(
        itemName = this.itemName,
        itemPrice = this.itemPrice,
        sellerNo = this.sellerNo,
        categoryNo = this.categoryNo
    )

    private fun HotdealItemResult.toResponse() = HotdealItemResponse(
        hotdealItemNo = this.hotdealItemNo,
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