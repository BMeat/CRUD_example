package com.example.demo.service.interfaces

import com.example.demo.dto.DetailDTO
import com.example.demo.dto.ListDTO
import com.example.demo.dto.PostFormDTO
import com.example.demo.dto.UpdateDTO
import org.springframework.http.ResponseEntity

interface BoardService {
    fun save(postFormDTO: PostFormDTO): ResponseEntity<*>;
    fun getAll(): List<ListDTO>;
    fun getDetail(id: Long, memberId: String): DetailDTO;

    fun remove(id: Long): ResponseEntity<*>;

    fun getUpdateDTO(id: Long): UpdateDTO;

    fun update(id: Long, updateDTO: UpdateDTO): ResponseEntity<*>;
}