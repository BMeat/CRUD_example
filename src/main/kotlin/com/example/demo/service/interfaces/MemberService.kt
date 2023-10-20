package com.example.demo.service.interfaces

import com.example.demo.dto.LoginDTO
import com.example.demo.dto.SignUpFormDTO
import org.springframework.http.ResponseEntity

interface MemberService {
    fun signUp(signUpFormDTO: SignUpFormDTO): ResponseEntity<*>;
    fun login(loginDTO: LoginDTO): ResponseEntity<*>;
}