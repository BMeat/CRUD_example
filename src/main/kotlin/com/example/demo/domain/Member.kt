package com.example.demo.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
class Member(id: String, password: String, username: String, memberRole: MemberRole) {
    @Id
    @Column(name = "member_id", nullable = false)
    var id: String = id;

    @Column(name = "member_password", nullable = false)
    var password: String = password;

    @Column(name = "member_name", nullable = false)
    var username: String = username

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    var memberRole: MemberRole = memberRole;
}