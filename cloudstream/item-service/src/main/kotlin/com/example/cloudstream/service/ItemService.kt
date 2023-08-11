package com.example.cloudstream.service

import com.example.cloudstream.exception.*
import com.example.cloudstream.repository.ItemRepository
import org.springframework.data.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ItemService(
    private val itemRepository: ItemRepository
) {
    fun minusQuantity(itemNo: Long, quantity: Long) {
        val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()
        item.minusQuantity(quantity)
    }
}