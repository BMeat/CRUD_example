package com.example.demo.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@Entity
@Table(name = "board")
@DynamicUpdate
class Board(title: String, content: String, member: Member): BaseTimeEntity() {
    // id 는 val 로 변경하는게 좋아보입니다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    val id: Long = 0L

    @Column(name = "board_title", nullable = false)
    var title: String = title
        protected set

    @Column(name = "board_content", nullable = false)
    var content: String = content
        protected set


    // createdAt, updatedAt 은 JPA의 auditing 기능을 사용하면 좀더 편리할거같습니다.
//    @Column(name = "board_createdAt", nullable = false)
//    var createdAt: LocalDateTime = LocalDateTime.now();
//
//    @Column(name = "board_updatedAt", nullable = false)
//    var updatedAt: LocalDateTime = LocalDateTime.now();

    // column 네이밍은 언더스코어로 고정하는게 좋습니다.
    @Column(name = "board_admin_views", nullable = false)
    var adminViews: Int = 0
        protected set

    /**
     * setter가 기본적으로 열려있는데 protected set 으로 변경해주세요.
     */
    @Column(name = "board_user_views", nullable = false)
    var userViews: Int = 0
        protected set

    // 세미콜론은 없애도 괜찮을거같습니다.
    @Column(name = "board_likes", nullable = false)
    var likes: Int = 0
        protected set

    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member = member
        protected set

    fun countAdminViews() {
        this.adminViews++
    }

    fun countUserViews() {
        this.userViews++
    }

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
        // JPA auditing 기능을 사용하면 다음과같은 코드는 불필요
//        this.updatedAt = LocalDateTime.now()
    }
}