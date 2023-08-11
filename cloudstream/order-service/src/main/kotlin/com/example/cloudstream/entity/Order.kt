package com.example.cloudstream.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "ORDERS")
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no", nullable = false)
    var orderNo: Long = 0,

    @Column(name = "item_no", nullable = false)
    var itemNo: Long,

    @Column(name = "member_no", nullable = false)
    var memberNo: Long,

    @Column(name = "insert_date", nullable = false, updatable = false)
    var insertDate: LocalDateTime = LocalDateTime.now()

)