package com.example.demo.service

import com.example.demo.domain.Member
import com.example.demo.domain.MemberRole
import com.example.demo.dto.LoginDTO
import com.example.demo.dto.SignUpFormDTO
import com.example.demo.exception.BusinessException
import com.example.demo.exception.ErrorCode
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
    override fun signUp(signUpFormDTO: SignUpFormDTO): String {
        // 반환타입을 직접 지정해주지 않아도 될거같습니다.
        val member = memberRepository.findById(signUpFormDTO.id)
        /**
         * https://cheese10yun.github.io/spring-guide-exception/
         * 를 참고하셔서 에러를 처리해보세요.
         */
        if (member.isPresent) {
            throw BusinessException(ErrorCode.ID_DUPLICATION)
        } else {
            val newMember: Member = Member(signUpFormDTO.id, signUpFormDTO.password, signUpFormDTO.name, MemberRole.USER);
            memberRepository.save(newMember);
            return "success"
        }
    }

    /**
     * 제네릭에 * (askterisk)를 사용하면 안될거같습니다.
     */
    override fun login(loginDTO: LoginDTO): String {
        val member = memberRepository.findById(loginDTO.id);
        if (member.isEmpty) {
            throw BusinessException(ErrorCode.LOGIN_INPUT_INVALID)
        } else {
            if (member.get().password == loginDTO.password) {
                return "success"
            } else {
                throw BusinessException(ErrorCode.LOGIN_INPUT_INVALID)
            }
        }
    }
}