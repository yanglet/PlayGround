package com.example.mysql.config

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> = Optional.of("mysql-api")
}