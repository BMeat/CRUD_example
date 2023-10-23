package com.example.demo.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@Entity
@DynamicUpdate
class Board(title: String, content: String, member: Member) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    var id: Long = 0L;

    @Column(name = "board_title", nullable = false)
    var title: String = title;

    @Column(name = "board_content", nullable = false)
    var content: String = content;

    @Column(name = "board_createdAt", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now();

    @Column(name = "board_updatedAt", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now();

    @Column(name = "board_adminViews", nullable = false)
    var adminViews: Int = 0;

    @Column(name = "board_userViews", nullable = false)
    var userViews: Int = 0;

    @Column(name = "board_likes", nullable = false)
    var likes: Int = 0;

    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member = member;

    fun countAdminViews() {
        this.adminViews++;
    }

    fun countUserViews() {
        this.userViews++;
    }

    fun update(title: String, content: String) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}