package com.example.cqrs.repository.command

import org.springframework.data.jpa.repository.JpaRepository

interface HotdealItemRepository : JpaRepository<HotdealItem, Long> {
}