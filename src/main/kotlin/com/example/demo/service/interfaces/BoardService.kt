package com.example.demo.service.interfaces

import com.example.demo.dto.DetailDTO
import com.example.demo.dto.ListDTO
import com.example.demo.dto.PostFormDTO
import com.example.demo.dto.UpdateDTO
import org.springframework.http.ResponseEntity

/**
 * BoardService 를 조회 와 생성을 분리하면 더 좋을거같습니다.
 * 그리고 굳이 Service에 interface 를 선언하고 구현하지 않아도 될거같습니다.
 */
interface BoardService {
    fun save(postFormDTO: PostFormDTO): String;
    fun getAll(): List<ListDTO>;
    fun getDetail(id: Long, memberId: String): DetailDTO;

    fun remove(id: Long): String

    fun getUpdateDTO(id: Long): UpdateDTO;

    fun update(id: Long, updateDTO: UpdateDTO): String
}