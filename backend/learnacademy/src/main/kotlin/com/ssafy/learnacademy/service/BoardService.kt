package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.BoardRepository
import com.ssafy.learnacademy.vo.Board
import org.springframework.stereotype.Service

@Service
class BoardService(var boardRepository: BoardRepository){

    fun findAll() : List<Board>?{
        return boardRepository.findAll()
    }

    fun findNoticeAllByAcademyId(academyId: Long) : List<Board>?{
        return boardRepository.findNoticeAllByAcademyId(academyId)
    }

    fun findReferenceAllByAcademyId(academyId: Long) : List<Board>?{
        return boardRepository.findReferenceAllByAcademyId(academyId)
    }

    fun findInquiryAllByAcademyId(academyId: Long): List<Board>? {
        return boardRepository.findInquiryAllByAcademyId(academyId)
    }

    fun findById(boardId : Long) : Board? {
        return boardRepository.findById(boardId).get()
    }

    fun insertBoard(board : Board) : Board?{
        return boardRepository.save(board)
    }

    fun updateBoard(board : Board) : Board?{
        return boardRepository.save(board)
    }

    fun deleteBoard(board: Board){
        return boardRepository.delete(board)
    }

}