package com.example.demo.controller

import com.example.demo.dto.ListDTO
import com.example.demo.service.interfaces.BoardService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class WebController(val boardService: BoardService) {
    @GetMapping("/")
    public fun index(model: Model): String {
        val posts : List<ListDTO> = boardService.getAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/signup")
    public fun signup(): String {
        return "signup";
    }

    @GetMapping("/signin")
    public fun signin(): String {
        return "signin";
    }

    @GetMapping("/new")
    public fun newPost(): String {
        return "new";
    }

    @GetMapping("/{id}")
    public fun detail(@PathVariable id : Long, model: Model, @CookieValue("id") memberId: String): String {
        val post = boardService.getDetail(id, memberId);
        model.addAttribute("post", post);
        return "detail";
    }
}