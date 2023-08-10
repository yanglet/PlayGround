package com.example.springevent.event;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberCreatedEvent {
    private Long memberNo;

    public static MemberCreatedEvent of(Long memberNo) {
        MemberCreatedEvent memberCreatedEvent = new MemberCreatedEvent();
        memberCreatedEvent.setMemberNo(memberNo);
        return memberCreatedEvent;
    }
}
