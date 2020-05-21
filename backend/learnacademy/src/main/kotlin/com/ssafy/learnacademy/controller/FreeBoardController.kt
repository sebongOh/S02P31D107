package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.FreeBoardService
import com.ssafy.learnacademy.vo.FreeBoard
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/freeBoard")
class FreeBoardController(var freeBoardService:FreeBoardService){

    @GetMapping("/")
    @ApiOperation(value = "자유게시판 전체찾기", notes = "자유게시판을 전부검색합니다")
    fun getAllFreeBoard() = freeBoardService.findAll()

    @GetMapping("/{freeBoardId}")
    @ApiOperation(value = "자유게시판 검색", notes = "자유게시판을 검색합니다")
    fun getFreeBoard(@PathVariable("freeBoardId") freeBoardId : Long) : Optional<FreeBoard>?{
        return freeBoardService.findById(freeBoardId)
    }

    @PostMapping
    @ApiOperation(value = "자유게시판 등록", notes = "자유게시판을 등록합니다")
    fun insertFreeBoard(@RequestBody freeBoard : FreeBoard) : FreeBoard?{
        return freeBoardService.insertFreeBoard(freeBoard)
    }

    @PutMapping
    @ApiOperation(value = "자유게시판 수정", notes = "자유게시판을 수정합니다")
    fun updateFreeBoard(@RequestBody freeBoard : FreeBoard) : FreeBoard?{
        return freeBoardService.updateFreeBoard(freeBoard)
    }

    @DeleteMapping("/{freeBoardId}")
    @ApiOperation(value = "자유게시판 삭제",notes = "자유게시판을 삭제합니다")
    fun deleteFreeBoard(@PathVariable("freeBoardId") freeBoardId : Long){
        return freeBoardService.deleteFreeBoard(freeBoardId)
    }
}