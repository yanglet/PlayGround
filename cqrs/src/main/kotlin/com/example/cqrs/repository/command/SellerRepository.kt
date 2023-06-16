package com.example.cqrs.repository.command

import org.springframework.data.jpa.repository.JpaRepository

interface SellerRepository : JpaRepository<Seller, Long> {
}