package com.example.concurrency.repository.entity

import com.example.concurrency.exception.*
import jakarta.persistence.*

@Entity
@Table(name = "ITEM")
class Item (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no", nullable = false)
    var itemNo: Long = 0,

    @Column(name = "item_name", nullable = false)
    var itemName: String,

    @Column(name = "item_quantity", nullable = false)
    var itemQuantity: Int

) : AbstractEntity() {
    fun minusQuantity() {
        validateItemQuantity()
        this.itemQuantity = this.itemQuantity - 1
    }

    private fun validateItemQuantity() {
        if (this.itemQuantity < 1) {
            throw ItemQuantityInsufficientException("상품의 재고가 부족합니다.")
        }
    }
}