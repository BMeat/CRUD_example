package com.example.demo.service

import com.example.demo.domain.Board
import com.example.demo.domain.Member
import com.example.demo.domain.MemberRole
import com.example.demo.dto.DetailDTO
import com.example.demo.dto.ListDTO
import com.example.demo.dto.PostFormDTO
import com.example.demo.repository.BoardRepository
import com.example.demo.repository.MemberRepository
import com.example.demo.service.interfaces.BoardService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
@Transactional
class BoardServiceImpl (var boardRepository: BoardRepository, var memberRepository: MemberRepository) : BoardService{
    override fun save(postFormDTO: PostFormDTO): ResponseEntity<*> {
        val member : Optional<Member> = memberRepository.findById(postFormDTO.author);
        if (member.isPresent) {
            val post : Board = Board(postFormDTO.title, postFormDTO.content, member.get());
            boardRepository.save(post);
            return ResponseEntity("success", HttpStatus.OK);
        }
        else {
            return ResponseEntity("fail", HttpStatus.OK);
        }
    }

    override fun getAll(): List<ListDTO> {
        val posts : List<Board> = boardRepository.findAll();
        val list : MutableList<ListDTO> = ArrayList<ListDTO>();

        for (post in posts) {
            val member : Member = post.member;
            val dto : ListDTO = ListDTO(post.id, post.title, post.content, post.createdAt, post.adminViews, post.userViews, post.member.username);
            list.add(dto);
        }

        return list;
    }

    override fun getDetail(id: Long, memberId: String): DetailDTO {
        val post: Board = boardRepository.findById(id).get();
        if (!post.id.equals(memberId)) {
            val member: Member = memberRepository.findById(memberId).get();
            if (member.memberRole.equals(MemberRole.USER)) {
                post.countUserViews();
            } else {
                post.countAdminViews();
            }
        }
        val detailDTO: DetailDTO = DetailDTO(
            post.id,
            post.title,
            post.content,
            post.createdAt,
            post.updatedAt,
            post.adminViews,
            post.userViews,
            post.member.username,
            post.likes,
        )
        return detailDTO;
    }
}