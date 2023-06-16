package com.example.cqrs.repository.command

import org.springframework.data.jpa.repository.JpaRepository

interface HotDealItemRepository : JpaRepository<HotDealItem, Long> {
}