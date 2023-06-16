package com.example.cqrs.common

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditingEntity {
  @Column(name = "ins_date", nullable = false, updatable = false)
  @CreatedDate
  lateinit var insertDate: Instant
    protected set

  @Column(name = "ins_oprt", nullable = false, updatable = false)
  @CreatedBy
  lateinit var insertOperator: String
    protected set

  @Column(name = "upd_date", nullable = false)
  @LastModifiedDate
  lateinit var updateDate: Instant
    protected set

  @Column(name = "upd_oprt", nullable = false)
  @LastModifiedBy
  lateinit var updateOperator: String
    protected set

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as AuditingEntity

    if (insertDate != other.insertDate) return false
    if (insertOperator != other.insertOperator) return false
    if (updateDate != other.updateDate) return false
    if (updateOperator != other.updateOperator) return false

    return true
  }

  override fun hashCode(): Int {
    var result = insertDate.hashCode()
    result = 31 * result + insertOperator.hashCode()
    result = 31 * result + updateDate.hashCode()
    result = 31 * result + updateOperator.hashCode()
    return result
  }
}