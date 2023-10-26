package com.example.demo.controller

import com.example.demo.dto.LoginDTO
import com.example.demo.dto.PostFormDTO
import com.example.demo.dto.SignUpFormDTO
import com.example.demo.dto.UpdateDTO
import com.example.demo.service.interfaces.BoardService
import com.example.demo.service.interfaces.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// RestController 로 변경하면 ResponseEntity 를 직접 써주지 않아도 된다.
@RestController
@RequestMapping("/api")
class ApiController(
    // private 가시성으로 만드는게 좋습니다.
    private val memberService: MemberService,
    private val boardService: BoardService
) {
    @PostMapping("/signup")
    // 기본이 public 이므로 생략 가능합니다.
    fun signUp(@RequestBody signUpFormDTO: SignUpFormDTO): String {
        return memberService.signUp(signUpFormDTO)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginDTO: LoginDTO): String {
        return memberService.login(loginDTO)
    }

    @PostMapping("/posts")
    fun newPost(@RequestBody postFormDTO: PostFormDTO): String {
        return boardService.save(postFormDTO)
    }

    @DeleteMapping("/posts/{id}")
    fun remove(@PathVariable id: Long): String {
        return boardService.remove(id)
    }

    @PatchMapping("/posts/{id}")
    fun update(@PathVariable id: Long, @RequestBody updateDTO: UpdateDTO): String {
        return boardService.update(id, updateDTO)
    }
}