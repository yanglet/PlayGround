package com.example.cqrs.repository.command

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(
    name = "DISCOUNT",
    indexes = [
        Index(name = "idx_name", columnList = "discount_name"),
        Index(name = "idx_rate", columnList = "discount_rate")
    ]
)
class Discount(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_no")
    var discountNo: Long,

    @Column(name = "discount_name")
    var discountName: String,

    @Column(name = "discount_rate")
    var discountRate: BigDecimal
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Discount

        if (discountNo != other.discountNo) return false
        if (discountName != other.discountName) return false
        if (discountRate != other.discountRate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = discountNo.hashCode()
        result = 31 * result + discountName.hashCode()
        result = 31 * result + discountRate.hashCode()
        return result
    }
}