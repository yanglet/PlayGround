package com.example.cqrs.service.command

import com.example.cqrs.repository.command.Seller
import com.example.cqrs.repository.command.SellerRepository
import com.example.cqrs.service.command.dto.CreateSellerParam
import com.example.cqrs.service.command.dto.SellerResult
import com.example.cqrs.service.command.dto.UpdateSellerParam
import com.example.cqrs.service.command.exception.SellerNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SellerService(
    private val sellerRepository: SellerRepository
) {
    fun createSeller(param: CreateSellerParam): SellerResult =
        sellerRepository.save(
            Seller(email = param.email, name = param.name)
        ).toDto()

    fun updateSeller(param: UpdateSellerParam): SellerResult {
        val seller = sellerRepository.findByIdOrNull(param.sellerNo)

        seller?.run {
            this.email = param.email
            this.name = param.name
        } ?: throw SellerNotFoundException()

        return seller.toDto()
    }

    private fun Seller.toDto() = SellerResult(
        sellerNo = this.sellerNo,
        email = this.email,
        name = this.name
    )
}