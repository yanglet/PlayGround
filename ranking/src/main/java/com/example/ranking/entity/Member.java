package com.example.ranking.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PRIVATE)
@Getter
public class Member extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no", nullable = false)
    private Long memberNo;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "score", nullable = false)
    private Long score;

    public static Member of(String memberName, Long score) {
        Member member = new Member();
        member.setMemberName(memberName);
        member.setScore(score);
        return member;
    }
}
