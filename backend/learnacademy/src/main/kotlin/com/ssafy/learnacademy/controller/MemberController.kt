package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.vo.Member
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/member")
class MemberController(var memberService: MemberService) {

    @GetMapping("/{memberId}")
    fun getMember(@PathVariable("memberId") memberId: Int): Optional<Member>? {
        return memberService.getMember(memberId)
    }

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