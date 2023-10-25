package com.example.demo.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class ListDTO(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val adminViews: Int,
    val userViews: Int,
    val memberName: String)