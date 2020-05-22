package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.NoticeBoardService
import com.ssafy.learnacademy.vo.NoticeBoard
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/noticeBoard")
class NoticeBoardController(var noticeBoardService: NoticeBoardService){

    @GetMapping
    @ApiOperation(value = "공지사항 전체검색", notes = "공지사항을 전부검색합니다")
    fun getAllNoticeBoard() = noticeBoardService.findAll()


    @GetMapping("/{noticeBoardId}")
    @ApiOperation(value = "공지사항 검색", notes = "공지사항을 검색합니다")
    fun getNoticeBoard(@PathVariable("noticeBoardId") noticeBoardId : Long) : Optional<NoticeBoard>?{
        return noticeBoardService.findById(noticeBoardId)
    }

    @PostMapping
    @ApiOperation(value="공지사항 등록", notes = "공지사항을 등록합니다")
    fun insertNoticeBoard(@RequestBody noticeBoard : NoticeBoard):NoticeBoard?{
        return noticeBoardService.insertNoticeBoard(noticeBoard)
    }

    @PutMapping
    @ApiOperation(value="공지사항 수정", notes = "공지사항을 수정합니다")
    fun updateNoticeBoard(@RequestBody noticeBoard : NoticeBoard) : NoticeBoard?{
        return noticeBoardService.updateNoticeBoard(noticeBoard)
    }

    @DeleteMapping("/{noticeBoardId}")
    @ApiOperation(value = "공지사항 삭제",notes = "공지사항을 삭제합니다")
    fun deleteNoticeBoard(@PathVariable("noticeBoardId") noticeBoardId : Long){
        return noticeBoardService.deleteNoticeBoard(noticeBoardId)
    }
}