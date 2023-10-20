package com.example.demo.controller

import com.example.demo.dto.LoginDTO
import com.example.demo.dto.PostFormDTO
import com.example.demo.dto.SignUpFormDTO
import com.example.demo.service.interfaces.BoardService
import com.example.demo.service.interfaces.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api")
class ApiController(val memberService: MemberService, val boardService: BoardService) {
    @PostMapping("/signup")
    public fun signUp(@RequestBody signUpFormDTO: SignUpFormDTO): ResponseEntity<*> {
        return memberService.signUp(signUpFormDTO);
    }

    @PostMapping("/login")
    public fun login(@RequestBody loginDTO: LoginDTO): ResponseEntity<*> {
        return memberService.login(loginDTO);
    }

    @PostMapping("/posts")
    public fun newPost(@RequestBody postFormDTO: PostFormDTO): ResponseEntity<*> {
        return boardService.save(postFormDTO);
    }
}