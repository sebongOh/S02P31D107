package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.NoticeBoardRepository
import com.ssafy.learnacademy.vo.NoticeBoard
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoticeBoardService(var noticeBoardRepository: NoticeBoardRepository) {

    fun findAll() : Iterable<NoticeBoard>?{
        return noticeBoardRepository.findAll()
    }

    fun findById(noticeBoardId : Int) : Optional<NoticeBoard>?{
        return noticeBoardRepository.findById(noticeBoardId)
    }

    fun insertNoticeBoard(noticeBoard: NoticeBoard) : NoticeBoard? {
        return noticeBoardRepository.save(noticeBoard)
    }

    fun updateNoticeBoard(noticeBoard: NoticeBoard) : NoticeBoard? {
        return noticeBoardRepository.save(noticeBoard)
    }

    fun deleteNoticeBoard(noticeBoardId: Int){
        return noticeBoardRepository.deleteById(noticeBoardId)
    }
}