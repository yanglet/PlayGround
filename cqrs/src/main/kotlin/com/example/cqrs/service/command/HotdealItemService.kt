package com.example.cqrs.service.command

import com.example.cqrs.repository.command.*
import com.example.cqrs.service.command.dto.*
import com.example.cqrs.service.command.exception.CategoryNotFoundException
import com.example.cqrs.service.command.exception.HotdealItemNotFoundException
import com.example.cqrs.service.command.exception.SellerNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class HotdealItemService(
    private val hotDealItemRepository: HotdealItemRepository,
    private val sellerRepository: SellerRepository,
    private val categoryRepository: CategoryRepository
) {
    @Transactional(readOnly = true)
    fun getHotdealItems(): Iterable<HotdealItemResult> =
        hotDealItemRepository.findAll().map { it.toDto() }

    fun createHotdealItem(param: CreateHotdealItemParam): HotdealItemResult {
        val seller = sellerRepository.findByIdOrNull(param.sellerNo) ?: throw SellerNotFoundException()
        val category = categoryRepository.findByIdOrNull(param.categoryNo) ?: throw CategoryNotFoundException()

        return hotDealItemRepository.save(
            HotdealItem(
                itemName = param.itemName,
                itemPrice = param.itemPrice,
                seller = seller,
                category = category
            )
        ).toDto()
    }

    fun updateHotdealItem(param: UpdateHotdealItemParam): HotdealItemResult {
        val hotDealItem = hotDealItemRepository.findByIdOrNull(param.hotDealItemNo)

        hotDealItem?.run {
            this.itemName = param.itemName ?: this.itemName
            this.itemPrice = param.itemPrice ?: this.itemPrice
        } ?: throw HotdealItemNotFoundException()

        return hotDealItem.toDto()
    }

    private fun HotdealItem.toDto() = HotdealItemResult(
        hotdealItemNo = this.hotdealItemNo,
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