package com.example.concurrency.repository

import com.example.concurrency.repository.entity.*
import jakarta.persistence.*
import org.springframework.data.jpa.repository.*
import org.springframework.data.jpa.repository.Query

interface OptimisticLockItemRepository : JpaRepository<OptimisticLockItem, Long> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT item from OptimisticLockItem item where item.itemNo = :itemNo")
    fun findByItemNoWithOptimisticLock(itemNo: Long): OptimisticLockItem?
}