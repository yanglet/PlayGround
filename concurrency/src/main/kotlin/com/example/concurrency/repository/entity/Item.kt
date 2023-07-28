package com.example.concurrency.repository.entity

import com.example.concurrency.exception.ItemQuantityInsufficientException
import jakarta.persistence.*

@Entity
@Table(name = "ITEM")
class Item (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_NO", nullable = false)
    var itemNo: Long = 0,

    @Column(name = "ITEM_NAME", nullable = false)
    var itemName: String,

    @Column(name = "ITEM_QUANTITY", nullable = false)
    var itemQuantity: Int,

    @Version
    var version: Long = 0

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