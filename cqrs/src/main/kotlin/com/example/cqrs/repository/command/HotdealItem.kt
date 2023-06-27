package com.example.cqrs.repository.command

import com.example.cqrs.common.AuditingEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.math.BigDecimal

@Entity
@Table(
    name = "HOTDEAL_ITEM",
    indexes = [
        Index(name = "idx_name", columnList = "hotdeal_item_name")
    ]
)
@DynamicUpdate
class HotdealItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotdeal_item_no", nullable = false)
    var hotdealItemNo: Long = 0L,

    @Column(name = "hotdeal_item_name", nullable = false)
    var itemName: String,

    @Column(name = "hotdeal_item_price", nullable = false)
    var itemPrice: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_no", nullable = false)
    var seller: Seller,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no", nullable = false)
    var category: Category
) : AuditingEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HotdealItem

        if (hotdealItemNo != other.hotdealItemNo) return false
        if (itemName != other.itemName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hotdealItemNo.hashCode()
        result = 31 * result + itemName.hashCode()
        return result
    }
}