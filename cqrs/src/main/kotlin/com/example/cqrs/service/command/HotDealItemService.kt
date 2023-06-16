package com.example.cqrs.service.command

import com.example.cqrs.repository.command.*
import com.example.cqrs.service.command.dto.*
import com.example.cqrs.service.command.exception.CategoryNotFoundException
import com.example.cqrs.service.command.exception.HotDealItemNotFoundException
import com.example.cqrs.service.command.exception.SellerNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class HotDealItemService(
    private val hotDealItemRepository: HotDealItemRepository,
    private val sellerRepository: SellerRepository,
    private val categoryRepository: CategoryRepository
) {
    fun createHotDealItem(param: CreateHotDealItemParam): HotDealItemResult {
        val seller = sellerRepository.findByIdOrNull(param.sellerNo) ?: throw SellerNotFoundException()
        val category = categoryRepository.findByIdOrNull(param.categoryNo) ?: throw CategoryNotFoundException()

        return hotDealItemRepository.save(
            HotDealItem(
                itemName = param.itemName,
                itemPrice = param.itemPrice,
                seller = seller,
                category = category
            )
        ).toDto()
    }

    fun updateHotDealItem(param: UpdateHotDealItemParam): HotDealItemResult {
        val hotDealItem = hotDealItemRepository.findByIdOrNull(param.hotDealItemNo)

        hotDealItem?.run {
            this.itemName = param.itemName ?: this.itemName
            this.itemPrice = param.itemPrice ?: this.itemPrice
        } ?: throw HotDealItemNotFoundException()

        return hotDealItem.toDto()
    }

    private fun HotDealItem.toDto() = HotDealItemResult(
        hotDealItemNo = this.hotDealItemNo,
        itemName = this.itemName,
        itemPrice = this.itemPrice,
        seller = this.seller.toDto(),
        category = this.category.toDto()
    )

    private fun Category.toDto() = CategoryResult(
        categoryNo = this.categoryNo,
        categoryName = this.categoryName
    )

    private fun Seller.toDto() = SellerResult(
        sellerNo = this.sellerNo,
        email = this.email,
        name = this.name
    )
}