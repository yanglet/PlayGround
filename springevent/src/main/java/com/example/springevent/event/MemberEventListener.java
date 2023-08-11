package com.example.springevent.event;

import com.example.springevent.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final CouponService couponService;

    @EventListener
    @Async
    public void createCoupon(MemberCreatedEvent event) {
        couponService.createCoupon(event.getMemberNo());
    }
}
