package com.example.cloudstream.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PayService {
    val log = LoggerFactory.getLogger(this.javaClass)

    fun pay(memberNo: Long) {
        log.info("결제가 완료되었습니다. 회원번호 = {}", memberNo)
    }
}