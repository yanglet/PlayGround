package com.example.cqrs.repository.command

import com.example.cqrs.common.AuditingEntity
import jakarta.persistence.*

@Entity
@Table(
    name = "CATEGORY",
    indexes = [
        Index(name = "idx_name", columnList = "category_name")
    ]
)
class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_no", nullable = false)
    var categoryNo: Long = 0L,

    @Column(name = "category_name", nullable = false)
    var categoryName: String
) : AuditingEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (categoryNo != other.categoryNo) return false
        if (categoryName != other.categoryName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = categoryNo.hashCode()
        result = 31 * result + categoryName.hashCode()
        return result
    }
}