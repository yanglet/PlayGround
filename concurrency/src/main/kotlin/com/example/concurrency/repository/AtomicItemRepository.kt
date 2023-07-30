package com.example.concurrency.repository

import com.example.concurrency.repository.entity.*
import org.springframework.data.jpa.repository.JpaRepository

interface AtomicItemRepository : JpaRepository<AtomicItem, Long> {
}