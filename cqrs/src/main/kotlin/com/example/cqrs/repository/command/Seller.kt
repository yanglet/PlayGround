package com.example.cqrs.repository.command

import com.example.cqrs.common.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "SELLER",
    indexes = [
        Index(name = "idx_email", columnList = "seller_email"),
        Index(name = "idx_name", columnList = "seller_name")
    ]
)
class Seller(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_no", nullable = false)
    var sellerNo: Long = 0L,

    @Column(name = "seller_email", nullable = false)
    var email: String,

    @Column(name = "seller_name", nullable = false)
    var name: String
) : AuditingEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Seller

        if (sellerNo != other.sellerNo) return false
        if (email != other.email) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sellerNo.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}