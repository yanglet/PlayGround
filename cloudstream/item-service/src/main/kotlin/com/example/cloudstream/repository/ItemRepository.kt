package com.example.cloudstream.repository

import com.example.cloudstream.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long> {
}