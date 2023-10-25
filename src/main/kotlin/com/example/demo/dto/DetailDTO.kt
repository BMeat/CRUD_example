package com.example.demo.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

/**
 * 단순히 데이터만 담는 클래스는 data 키워드를 앞에 붙여주도록 합시다
 */
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
    var memberId: String,
    var memberName: String,
    var likes: Int,
) {

}