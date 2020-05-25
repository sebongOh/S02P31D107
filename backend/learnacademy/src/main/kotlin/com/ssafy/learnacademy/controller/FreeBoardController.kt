package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.FreeBoardService
import com.ssafy.learnacademy.vo.FreeBoard
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/freeBoard")
class FreeBoardController(var freeBoardService:FreeBoardService){

    @GetMapping
    @ApiOperation(value = "자유게시판 전체찾기", notes = "자유게시판을 전부검색합니다")
    fun getAllFreeBoard() : List<FreeBoard>? = freeBoardService.findAll()

    @GetMapping("/{freeBoardId}")
    @ApiOperation(value = "자유게시판 검색", notes = "자유게시판을 검색합니다")
    fun getFreeBoard(@PathVariable("freeBoardId") freeBoardId : Long) : ResponseEntity<FreeBoard>?{
        return freeBoardService.findById(freeBoardId)?.map{ freeBoard ->
            ResponseEntity.ok(freeBoard)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @PostMapping
    @ApiOperation(value = "자유게시판 등록", notes = "자유게시판을 등록합니다")
    fun insertFreeBoard(@RequestBody freeBoard : FreeBoard) : FreeBoard?{
        return freeBoardService.insertFreeBoard(freeBoard)
    }

    @PutMapping
    @ApiOperation(value = "자유게시판 수정", notes = "자유게시판을 수정합니다")
    fun updateFreeBoard(@RequestBody freeBoard : FreeBoard) : ResponseEntity<FreeBoard>?{
        return freeBoardService.findById(freeBoard.freeBoardId)?.map {
            freeBoardService.updateFreeBoard(freeBoard)
            ResponseEntity<FreeBoard>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @DeleteMapping("/{freeBoardId}")
    @ApiOperation(value = "자유게시판 삭제",notes = "자유게시판을 삭제합니다")
    fun deleteFreeBoard(@PathVariable("freeBoardId") freeBoardId : Long):ResponseEntity<Void>?{
        return freeBoardService.findById(freeBoardId)?.map { freeBoard->
            freeBoardService.deleteFreeBoard(freeBoard)
            ResponseEntity<Void>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }
}