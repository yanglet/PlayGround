package com.example.mysql.repository

import jakarta.persistence.*

@Entity
@Table(name = "MEMBER")
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no", nullable = false)
    var memberNo: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String
) {
}