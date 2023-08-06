package com.example.concurrency.repository.entity

import com.example.concurrency.converter.AtomicIntegerConverter
import com.example.concurrency.exception.ItemQuantityInsufficientException
import jakarta.persistence.*
import java.util.concurrent.atomic.AtomicInteger

@Entity
@Table(name = "ATOMIC_ITEM")
class AtomicItem (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no", nullable = false)
    var itemNo: Long = 0,

    @Column(name = "item_name", nullable = false)
    var itemName: String,

    @Convert(converter = AtomicIntegerConverter::class)
    @Column(name = "item_quantity", nullable = false)
    var itemQuantity: AtomicInteger = AtomicInteger(0)

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