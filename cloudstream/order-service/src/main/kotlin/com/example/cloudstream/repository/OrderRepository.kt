package com.example.cloudstream.repository

import com.example.cloudstream.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}