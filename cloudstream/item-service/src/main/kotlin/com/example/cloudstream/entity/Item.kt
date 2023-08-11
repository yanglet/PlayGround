package com.example.cloudstream.entity

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@Entity
@Table(name = "ITEM")
@DynamicUpdate
class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no", nullable = false)
    var itemNo: Long = 0,

    @Column(name = "member_no", nullable = false)
    var memberNo: Long,

    @Column(name = "quantity", nullable = false)
    var quantity: Long,

    @Column(name = "insert_date", nullable = false, updatable = false)
    var insertDate: LocalDateTime = LocalDateTime.now()

) {
    fun minusQuantity(quantity: Long) {
        this.quantity = this.quantity - quantity
    }
}