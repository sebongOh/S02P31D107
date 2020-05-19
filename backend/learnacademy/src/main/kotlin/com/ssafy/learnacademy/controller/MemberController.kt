package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.vo.Member
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/member")
class MemberController(var memberService: MemberService) {

    @GetMapping("/{memberId}")
    @ApiOperation(value = "멤버 찾기", notes = "멤버를 검색합니다")
    fun getMember(@PathVariable("memberId") memberId: Int): Optional<Member>? {
        return memberService.getMember(memberId)
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
    fun deleteMember(@PathVariable("memberId") memberId: Int) {
        return memberService.deleteMember(memberId)
    }

}