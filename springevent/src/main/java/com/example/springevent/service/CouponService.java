package com.example.springevent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

    public void createCoupon(Long memberNo) {
        log.info("쿠폰이 정상적으로 발급되었습니다! 회원번호 = {}", memberNo);
    }
}
