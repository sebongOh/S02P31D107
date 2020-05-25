package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.NoticeBoardService
import com.ssafy.learnacademy.vo.NoticeBoard
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/noticeBoard")
class NoticeBoardController(var noticeBoardService: NoticeBoardService){

    @GetMapping
    @ApiOperation(value = "공지사항 전체검색", notes = "공지사항을 전부검색합니다")
    fun getAllNoticeBoard() : List<NoticeBoard>? = noticeBoardService.findAll()


    @GetMapping("/{noticeBoardId}")
    @ApiOperation(value = "공지사항 검색", notes = "공지사항을 검색합니다")
    fun getNoticeBoard(@PathVariable("noticeBoardId") noticeBoardId : Long) : ResponseEntity<NoticeBoard>?{
        return noticeBoardService.findById(noticeBoardId)?.map { noticeBoard ->
            ResponseEntity.ok(noticeBoard)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @PostMapping
    @ApiOperation(value="공지사항 등록", notes = "공지사항을 등록합니다")
    fun insertNoticeBoard(@RequestBody noticeBoard : NoticeBoard):NoticeBoard?{
        return noticeBoardService.insertNoticeBoard(noticeBoard)
    }

    @PutMapping
    @ApiOperation(value="공지사항 수정", notes = "공지사항을 수정합니다")
    fun updateNoticeBoard(@RequestBody noticeBoard : NoticeBoard) : ResponseEntity<NoticeBoard>?{
        return noticeBoardService.findById(noticeBoard.noticeBoardId)?.map {
            noticeBoardService.updateNoticeBoard(noticeBoard)
            ResponseEntity<NoticeBoard>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @DeleteMapping("/{noticeBoardId}")
    @ApiOperation(value = "공지사항 삭제",notes = "공지사항을 삭제합니다")
    fun deleteNoticeBoard(@PathVariable("noticeBoardId") noticeBoardId : Long) : ResponseEntity<Void>? {
        return noticeBoardService.findById(noticeBoardId)?.map { noticeBoard->
            noticeBoardService.deleteNoticeBoard(noticeBoard)
            ResponseEntity<Void>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }
}