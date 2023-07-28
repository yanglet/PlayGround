package com.example.concurrency.service

import com.example.concurrency.exception.ItemNotFoundException
import com.example.concurrency.repository.ItemRepository
import com.example.concurrency.repository.entity.Item
import jakarta.persistence.EntityManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ItemService(
    private val itemRepository: ItemRepository,
    private val entityManager: EntityManager
) {
    val log: Logger = LoggerFactory.getLogger(javaClass)

    fun saveItem(itemName: String, itemQuantity: Int): Long {
        return itemRepository.save(
            Item(itemName = itemName, itemQuantity = itemQuantity)
        ).itemNo
    }

    fun minusItemQuantity(itemNo: Long) {
        val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = itemRepository.saveAndFlush(item)

        log.info("[AFTER] itemNo = ${saveAndFlush.itemNo}, itemQuantity = ${saveAndFlush.itemQuantity}")
    }

    @Synchronized
    fun minusItemQuantityWithSynchronized(itemNo: Long) {
        val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = itemRepository.saveAndFlush(item)

        log.info("[AFTER] itemNo = ${saveAndFlush.itemNo}, itemQuantity = ${saveAndFlush.itemQuantity}")
    }

    fun minusItemQuantityWithOptimisticLock(itemNo: Long) {
        val item = itemRepository.findByItemNoWithOptimisticLock(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = itemRepository.saveAndFlush(item)

        log.info("[AFTER] itemNo = ${saveAndFlush.itemNo}, itemQuantity = ${saveAndFlush.itemQuantity}")
    }

    fun minusItemQuantityWithPessimisticLock(itemNo: Long) {
        val item = itemRepository.findByItemNoWithPessimisticLock(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = itemRepository.saveAndFlush(item)

        log.info("[AFTER] itemNo = ${saveAndFlush.itemNo}, itemQuantity = ${saveAndFlush.itemQuantity}")
    }
}