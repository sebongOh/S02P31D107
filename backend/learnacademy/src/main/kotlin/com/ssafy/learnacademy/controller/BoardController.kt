package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.service.BoardService
import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.vo.Board
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/board")
class BoardController(
        val boardService:BoardService,
        val memberService: MemberService,
        val academyService: AcademyService
    ){

    @GetMapping
    @ApiOperation(value = "게시판 전체찾기", notes = "자유게시판을 전부검색합니다")
    fun getAllBoard() : ResponseEntity<List<Board>>? {
        val board : List<Board>? = boardService.findAll() ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(board)
    }

    @GetMapping("/academy/{academyId}/notice")
    @ApiOperation(value = "학원별 공지사항 전체찾기", notes = "자유게시판을 전부검색합니다")
    fun getAllNoticeBoardByAcademyId(@PathVariable("academyId") academyId: Long) : ResponseEntity<List<Board>>? {
        val board : List<Board>? = boardService.findNoticeAllByAcademyId(academyId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(board)
    }

    @GetMapping("/academy/{academyId}/reference")
    @ApiOperation(value = "학원별 자료게시판 전체찾기", notes = "자유게시판을 전부검색합니다")
    fun getAllReferenceBoardByAcademyId(@PathVariable("academyId") academyId: Long) : ResponseEntity<List<Board>>? {
        val board : List<Board>? = boardService.findReferenceAllByAcademyId(academyId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(board)
    }

    @GetMapping("/academy/{academyId}/inquiry")
    @ApiOperation(value = "학원별 1:1문의 게시판 전체찾기", notes = "자유게시판을 전부검색합니다")
    fun getAllInquiryBoardByAcademyId(@PathVariable("academyId") academyId: Long) : ResponseEntity<List<Board>>? {
        val board : List<Board>? = boardService.findInquiryAllByAcademyId(academyId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(board)
    }

    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시판 검색", notes = "자유게시판을 검색합니다")
    fun getBoard(@PathVariable("boardId") boardId : Long) : ResponseEntity<Board>?{
        val board :Board? =  boardService.findById(boardId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(board)
    }

    @PostMapping("/notice")
    @ApiOperation(value = "공지사항 등록", notes = "공지사항을 등록합니다")
    fun insertNoticeBoard(@RequestBody board : Board) : ResponseEntity<Board>?{
        board.type = 0
        board.member = memberService.getMember()
        board.academy = academyService.getAcademy(board.academy?.academyId!!)
        board.contents = board.contents?.replace("\n", "<br>")
        board.create_at = LocalDateTime.now().toString()
        val insertBoard : Board? = boardService.insertBoard(board) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(insertBoard)
    }

    @PostMapping("/reference")
    @ApiOperation(value = "자료게시판 등록", notes = "자료게시판을 등록합니다")
    fun insertReferenceBoard(@RequestBody board : Board) : ResponseEntity<Board>?{
        board.type = 1
        board.member = memberService.getMember()
        board.academy = academyService.getAcademy(board.academy?.academyId!!)
        board.contents = board.contents?.replace("\n", "<br>")
        board.create_at = LocalDateTime.now().toString()
        val insertBoard : Board? = boardService.insertBoard(board) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(insertBoard)
    }

    @PostMapping("/inquiry")
    @ApiOperation(value = "1:1문의 게시판 등록", notes = "1:1문의를 등록합니다")
    fun insertInquiryBoard(@RequestBody board : Board) : ResponseEntity<Board>?{
        board.type = 2
        board.member = memberService.getMember()
        board.academy = academyService.getAcademy(board.academy?.academyId!!)
        board.contents = board.contents?.replace("\n", "<br>")
        board.create_at = LocalDateTime.now().toString()
        val insertBoard : Board? = boardService.insertBoard(board) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(insertBoard)
    }

    @PutMapping
    @ApiOperation(value = "게시판 수정", notes = "자유게시판을 수정합니다")
    fun updateBoard(@RequestBody board : Board) : ResponseEntity<Board>?{
        boardService.findById(board.boardId!!) ?: return ResponseEntity.noContent().build()
        board.contents = board.contents?.replace("\n", "<br>")
        boardService.updateBoard(board)
        return ResponseEntity.ok().body(board)
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "게시판 삭제",notes = "자유게시판을 삭제합니다")
    fun deleteBoard(@PathVariable("boardId") boardId : Long):ResponseEntity<Unit>? {
        val board : Board? = boardService.findById(boardId) ?: return ResponseEntity.notFound().build()
        boardService.deleteBoard(board!!)
        return ResponseEntity.ok().build()
    }
}