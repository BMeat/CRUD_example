package com.example.demo.service

import com.example.demo.domain.Member
import com.example.demo.domain.MemberRole
import com.example.demo.dto.LoginDTO
import com.example.demo.dto.SignUpFormDTO
import com.example.demo.repository.MemberRepository
import com.example.demo.service.interfaces.MemberService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
@Transactional
class MemberServiceImpl(var memberRepository: MemberRepository) : MemberService {
    override fun signUp(signUpFormDTO: SignUpFormDTO): ResponseEntity<*> {
        val member : Optional<Member> = memberRepository.findById(signUpFormDTO.id);
        if (member.isPresent) {
            return ResponseEntity("fail", HttpStatus.OK);
        } else {
            val newMember: Member = Member(signUpFormDTO.id, signUpFormDTO.password, signUpFormDTO.name, MemberRole.USER);
            memberRepository.save(newMember);
            return ResponseEntity("success", HttpStatus.OK);
        }
    }

    override fun login(loginDTO: LoginDTO): ResponseEntity<*> {
        val member : Optional<Member> = memberRepository.findById(loginDTO.id);
        if (member.isEmpty) {
            return ResponseEntity("id not found", HttpStatus.OK);
        } else {
            if (member.get().password == loginDTO.password) {
                return ResponseEntity("success", HttpStatus.OK);
            } else {
                return ResponseEntity("password not matched", HttpStatus.OK);
            }
        }
    }
}