package com.example.demo.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

class ListDTO(var id: Long, var title: String, var content: String, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") var createdAt: LocalDateTime, var adminViews: Int, var userViews: Int, var memberName: String) {

}