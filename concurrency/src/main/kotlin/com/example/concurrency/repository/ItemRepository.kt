package com.example.concurrency.repository

import com.example.concurrency.repository.entity.Item
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface ItemRepository : JpaRepository<Item, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT item from Item item where item.itemNo = :itemNo")
    fun findByItemNoWithPessimisticLock(itemNo: Long): Item?

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT item from Item item where item.itemNo = :itemNo")
    fun findByItemNoWithOptimisticLock(itemNo: Long): Item?
}