package com.example.demo.repository

import com.example.demo.domain.Board
import com.example.demo.dto.ListDTO
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
}