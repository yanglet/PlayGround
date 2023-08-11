package com.example.mysql.config

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity {
  @Column(name = "insert_date", nullable = false, updatable = false)
  @CreatedDate
  var insertDate: LocalDateTime = LocalDateTime.now()
    protected set

  @Column(name = "insert_operator", nullable = false, updatable = false)
  @CreatedBy
  var insertOperator: String = ""
    protected set

  @Column(name = "update_date", nullable = false)
  @LastModifiedDate
  var updateDate: LocalDateTime = LocalDateTime.now()
    protected set

  @Column(name = "update_operator", nullable = false)
  @LastModifiedBy
  var updateOperator: String = ""
    protected set
}