package com.example.repository;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER_UPDATE_EVENT_QUEUE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberUpdateEventQueue {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_update_event_queue_no", nullable = false)
    private Long memberUpdateEventQueueNo;

    @Column(name = "member_no", nullable = false)
    private Long memberNo;

    public static MemberUpdateEventQueue of(Long memberNo) {
        MemberUpdateEventQueue memberUpdateEventQueue = new MemberUpdateEventQueue();
        memberUpdateEventQueue.memberNo = memberNo;
        return memberUpdateEventQueue;
    }
}
