package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.vo.Member
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping("/member")
class MemberController(var memberService: MemberService) {

//    @GetMapping("/{memberId}")
//    @ApiOperation(value = "멤버 찾기", notes = "멤버를 검색합니다")
//    fun getMember(@PathVariable("memberId") memberId: Long): Member? {
//        return memberService.getMember(memberId)
//    }

    @GetMapping("/{memberId}")
    @ApiOperation(value = "멤버 찾기", notes = "멤버를 검색합니다")
    fun getMember(@PathVariable("memberId") memberId: Long): ResponseEntity<Member> {
        var member: Member? = memberService.getMember(memberId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(member)
    }

    // 학원 아이디로 연결된 학원 계정 조회
    
    @PostMapping
    fun insertMember(@RequestBody member: Member): Member? {
        return memberService.insertMember(member)
    }

    @PutMapping
    fun updateMember(@RequestBody member: Member): Member? {
        return memberService.updateMember(member)
    }

    @DeleteMapping("/{memberId}")
    fun deleteMember(@PathVariable("memberId") memberId: Long): ResponseEntity<Unit> {
        try {
            memberService.deleteMember(memberId)
        } catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok().build()
    }

}