package com.example.demo.service

import com.example.demo.domain.Board
import com.example.demo.domain.Member
import com.example.demo.domain.MemberRole
import com.example.demo.dto.DetailDTO
import com.example.demo.dto.ListDTO
import com.example.demo.dto.PostFormDTO
import com.example.demo.dto.UpdateDTO
import com.example.demo.repository.BoardRepository
import com.example.demo.repository.MemberRepository
import com.example.demo.service.interfaces.BoardService
import jakarta.transaction.Transactional
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
@Transactional
class BoardServiceImpl(var boardRepository: BoardRepository, var memberRepository: MemberRepository) : BoardService {
    override fun save(postFormDTO: PostFormDTO): String {
        val member: Optional<Member> = memberRepository.findById(postFormDTO.memberId)
        if (member.isPresent) {
            val post: Board = Board(postFormDTO.title, postFormDTO.content, member.get())
            boardRepository.save(post)
            return "success"
        } else {
            return "fail"
        }
    }

    override fun getAll(): List<ListDTO> {
        // 이와같이 변경하면 더 좋을거같습니다.
//        return boardRepository.findAll().map { board ->
//            ListDTO(
//                id = board.id,
//                title = board.title,
//                content = board.content,
//                createdAt = board.createdAt,
//                adminViews = board.adminViews,
//                userViews = board.userViews,
//                memberName = board.member.username,
//            )
//        }


        val posts: List<Board> = boardRepository.findAll()
        // 빈 배열이 필요하다면 mutableListOf() 를 사용해주세요.
        val list: MutableList<ListDTO> = ArrayList<ListDTO>()

        for (post in posts) {
            val member = post.member
            val dto = ListDTO(
                post.id,
                post.title,
                post.content,
                post.createdAt,
                post.adminViews,
                post.userViews,
                post.member.username
            )
            list.add(dto)
        }

        return list
    }

    override fun getDetail(id: Long, memberId: String): DetailDTO {
        val post = boardRepository.findById(id).get()
        // 여기서 post 가 조회되지않았을 때의 예외처리가 빠져있긴하네요  귀찮으면 안하셔도 됩니다.
        if (!post.member.id.equals(memberId)) {
            if (post.member.memberRole.equals(MemberRole.USER)) {
                post.countUserViews()
            } else {
                post.countAdminViews()
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
            post.member.id,
            post.member.username,
            post.likes,
        )
        return detailDTO
    }

    override fun remove(id: Long): String {
        boardRepository.deleteById(id)
        return "success"
    }

    override fun getUpdateDTO(id: Long): UpdateDTO {
        val post: Board = boardRepository.findById(id).get()
        val updateDTO = UpdateDTO(post.id, post.title, post.content)
        return updateDTO
    }

    override fun update(id: Long, updateDTO: UpdateDTO): String {
        var post = boardRepository.findById(id).get()
        post.update(updateDTO.title, updateDTO.content)
        return "success"
    }
}