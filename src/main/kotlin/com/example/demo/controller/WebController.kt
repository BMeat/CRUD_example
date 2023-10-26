package com.example.demo.controller

import com.example.demo.dto.ListDTO
import com.example.demo.dto.UpdateDTO
import com.example.demo.service.interfaces.BoardService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class WebController(private val boardService: BoardService) {
    @GetMapping("/")
    fun index(model: Model): String {
        val posts : List<ListDTO> = boardService.getAll()
        model.addAttribute("posts", posts)
        return "home"
    }

    @GetMapping("/signup")
    fun signup(): String {
        return "signup"
    }

    @GetMapping("/signin")
    fun signin(): String {
        return "signin"
    }

    @GetMapping("/new")
    fun newPost(): String {
        return "new"
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable id : Long, model: Model, @CookieValue("id") memberId: String): String {
        val post = boardService.getDetail(id, memberId)
        model.addAttribute("post", post)
        return "detail"
    }

    @GetMapping("/update/{id}")
    fun update(@PathVariable id : Long, model: Model): String {
        val post: UpdateDTO = boardService.getUpdateDTO(id)
        model.addAttribute("post", post)
        return "update"
    }
}