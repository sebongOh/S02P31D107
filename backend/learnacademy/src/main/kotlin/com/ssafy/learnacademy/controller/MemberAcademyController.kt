package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberAcademyService
import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/member-academy")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class MemberAcademyController (
        val memberAcademyService: MemberAcademyService,
        val memberService: MemberService
) {

    @GetMapping("/{memberAcademyId}")
    fun getMemberAcademy(@PathVariable("memberAcademyId") memberAcademyId: Long): ResponseEntity<MemberAcademy>? {
        return ResponseEntity.ok().body(memberAcademyService.getMemberAcademy(memberAcademyId))
    }

    // 일반 회원 아이디로 조회 (일반 회원이 등록한 학원들 조회)
    @GetMapping("/member")
    fun getMemberAcademyByMemberId(): ResponseEntity<MutableList<Academy>>? {
        val member: Member = memberService.getMember() ?: return ResponseEntity.notFound().build()
        val academyList : MutableList<Academy> = memberAcademyService.findByMemberId(member.memberId ?: 0)
        return ResponseEntity.ok().body(academyList)
    }

    @GetMapping("/check-academy/{academyId}")
    fun checkMemberAcademy(@PathVariable("academyId") academyId: Long): ResponseEntity<Any>? {
        val member: Member = memberService.getMember() ?: return ResponseEntity.notFound().build()
        val academyList : MutableList<Academy> = memberAcademyService.findByMemberId(member.memberId ?: 0)
        for (a: Academy in academyList) {
            if (a.academyId == academyId) {
                return ResponseEntity.ok().build()
            }
        }
        return ResponseEntity.noContent().build()
    }

    // 학원 아이디로 조회 (학원에 등록된 원생들 조회)
    @GetMapping("/academy/{academyId}")
    fun getMemberAcademyByAcademyId(@PathVariable("academyId") academyId: Long): ResponseEntity<MutableList<Member>>? {
        val memberList : MutableList<Member> = memberAcademyService.findByAcademyId(academyId)
        return ResponseEntity.ok().body(memberList)
    }

    @PostMapping
    fun insertMemberAcademy(@RequestBody memberAcademy: MemberAcademy): MemberAcademy? {
        var member: Member? = memberService.getMember()
        memberAcademy.member = member
        return memberAcademyService.insertMemberAcademy(memberAcademy)
    }

    @DeleteMapping("/{memberAcademyId}")
    fun deleteMemberAcademy(@PathVariable("memberAcademyId") memberAcademyId: Long) {
        return memberAcademyService.deleteMemberAcademy(memberAcademyId)
    }
}