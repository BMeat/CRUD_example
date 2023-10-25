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
        // 반환타입을 직접 지정해주지 않아도 될거같습니다.
        val member : Optional<Member> = memberRepository.findById(signUpFormDTO.id);
        if (member.isPresent) {
            /**
             * https://cheese10yun.github.io/spring-guide-exception/
             * 를 참고하셔서 에러를 처리해보세요.
             */
            return ResponseEntity("fail", HttpStatus.OK);
        } else {
            val newMember: Member = Member(signUpFormDTO.id, signUpFormDTO.password, signUpFormDTO.name, MemberRole.USER);
            memberRepository.save(newMember);
            return ResponseEntity("success", HttpStatus.OK);
        }
    }

    /**
     * 제네릭에 * (askterisk)를 사용하면 안될거같습니다.
     */
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