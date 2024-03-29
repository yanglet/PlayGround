package com.example.concurrency.service

import com.example.concurrency.aop.annotation.*
import com.example.concurrency.exception.*
import com.example.concurrency.repository.*
import com.example.concurrency.repository.entity.*
import org.slf4j.*
import org.springframework.data.repository.*
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.*
import java.util.concurrent.atomic.*

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val optimisticLockItemRepository: OptimisticLockItemRepository,
    private val atomicItemRepository: AtomicItemRepository
) {
    val log: Logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun saveItem(itemName: String, itemQuantity: Int): Long {
        return itemRepository.save(
            Item(itemName = itemName, itemQuantity = itemQuantity)
        ).itemNo
    }

    @Transactional
    fun saveOptimisticLockItem(itemName: String, itemQuantity: Int): Long {
        return optimisticLockItemRepository.save(
            OptimisticLockItem(itemName = itemName, itemQuantity = itemQuantity)
        ).itemNo
    }

    @Transactional
    fun saveAtomicItem(itemName: String, itemQuantity: Int): Long {
        return atomicItemRepository.save(
            AtomicItem(itemName = itemName, itemQuantity = AtomicInteger(itemQuantity))
        ).itemNo
    }

    @Transactional
    fun minusItemQuantity(itemNo: Long) {
        val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = itemRepository.saveAndFlush(item)

        log.info("[AFTER] itemNo = ${saveAndFlush.itemNo}, itemQuantity = ${saveAndFlush.itemQuantity}")
    }

    @Transactional
    fun minusAtomicItemQuantity(itemNo: Long) {
        val item = atomicItemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = atomicItemRepository.saveAndFlush(item)

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

    @Transactional
    fun minusItemQuantityWithOptimisticLock(itemNo: Long) {
        val item = optimisticLockItemRepository.findByItemNoWithOptimisticLock(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = optimisticLockItemRepository.saveAndFlush(item)

        log.info("[AFTER] itemNo = ${saveAndFlush.itemNo}, itemQuantity = ${saveAndFlush.itemQuantity}")
    }

    @Transactional
    fun minusItemQuantityWithPessimisticLock(itemNo: Long) {
        val item = itemRepository.findByItemNoWithPessimisticLock(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = itemRepository.saveAndFlush(item)

        log.info("[AFTER] itemNo = ${saveAndFlush.itemNo}, itemQuantity = ${saveAndFlush.itemQuantity}")
    }

    @RedissonLock(key = "#itemNo")
    fun minusItemQuantityWithRedissonLock(itemNo: Long) {
        val item = itemRepository.findByIdOrNull(itemNo) ?: throw ItemNotFoundException()

        log.info("[BEFORE] itemNo = ${item.itemNo}, itemQuantity = ${item.itemQuantity}")

        item.minusQuantity()

        val saveAndFlush = itemRepository.saveAndFlush(item)

        log.info("[AFTER] itemNo = ${saveAndFlush.itemNo}, itemQuantity = ${saveAndFlush.itemQuantity}")
    }
}