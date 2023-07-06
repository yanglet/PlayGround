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
    var category: Category,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_no")
    var discount: Discount? = null
) : AuditingEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as HotdealItem

        if (hotdealItemNo != other.hotdealItemNo) return false
        if (itemName != other.itemName) return false
        if (itemPrice != other.itemPrice) return false
        if (seller != other.seller) return false
        if (category != other.category) return false
        if (discount != other.discount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + hotdealItemNo.hashCode()
        result = 31 * result + itemName.hashCode()
        result = 31 * result + itemPrice.hashCode()
        result = 31 * result + seller.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + (discount?.hashCode() ?: 0)
        return result
    }
}