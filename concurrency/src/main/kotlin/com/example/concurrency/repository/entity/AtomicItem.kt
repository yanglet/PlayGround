package com.example.concurrency.repository.entity

import com.example.concurrency.exception.*
import jakarta.persistence.*
import java.util.concurrent.atomic.*

@Entity
@Table(name = "ATOMIC_ITEM")
class AtomicItem (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_NO", nullable = false)
    var itemNo: Long = 0,

    @Column(name = "ITEM_NAME", nullable = false)
    var itemName: String,

    @Column(name = "ITEM_QUANTITY", nullable = false)
    var itemQuantity: AtomicInteger

) : AbstractEntity() {
    fun minusQuantity() {
        validateItemQuantity()
        this.itemQuantity.getAndDecrement()
    }

    private fun validateItemQuantity() {
        if (this.itemQuantity.get() < 1) {
            throw ItemQuantityInsufficientException("상품의 재고가 부족합니다.")
        }
    }
}