package com.example.demo.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

// Table 어노테이션도 추가해주시면 감사하겠습니다.
@Entity
@Table(name = "member")
@DynamicUpdate // 이런게 있는지 몰랐네요
class Member(id: String, password: String, username: String, memberRole: MemberRole) {
    @Id
    @Column(name = "member_id", nullable = false)
    val id: String = id

    @Column(name = "member_password", nullable = false)
    var password: String = password
        protected set

    @Column(name = "member_name", nullable = false)
    var username: String = username
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    var memberRole: MemberRole = memberRole
        protected set
}