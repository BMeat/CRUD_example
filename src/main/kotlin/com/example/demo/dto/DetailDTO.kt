package com.example.demo.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

class DetailDTO(
    var id: Long,
    var title: String,
    var content: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime,
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: LocalDateTime,
    var adminViews: Int,
    var userViews: Int,
    var author: String,
    var likes: Int,
) {

}