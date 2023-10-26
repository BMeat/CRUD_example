package com.example.demo.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

/**
 * 단순히 데이터만 담는 클래스는 data 키워드를 앞에 붙여주도록 합시다
 */
data class DetailDTO(
    val id: Long,
    val title: String,
    val content: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime,
    val adminViews: Int,
    val userViews: Int,
    val memberId: String,
    val memberName: String,
    val likes: Int,
)