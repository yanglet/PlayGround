package com.example.cqrs.configuration

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuditorAware : AuditorAware<String> {
  override fun getCurrentAuditor(): Optional<String> {
    val userId = "cqrs"

    return Optional.of(userId)
  }
}