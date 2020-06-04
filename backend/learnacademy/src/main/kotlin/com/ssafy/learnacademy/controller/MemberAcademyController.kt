package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberAcademyService
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
class MemberAcademyController (val memberAcademyService: MemberAcademyService) {

    @GetMapping("/{memberAcademyId}")
    fun getMemberAcademy(@PathVariable("memberAcademyId") memberAcademyId: Long): ResponseEntity<MemberAcademy>? {
        return ResponseEntity.ok().body(memberAcademyService.getMemberAcademy(memberAcademyId))
    }

    // 일반 회원 아이디로 조회 (일반 회원이 등록한 학원들 조회)
    @GetMapping("/member/{memberId}")
    fun getMemberAcademyByMemberId(@PathVariable("memberId") memberId: Long): ResponseEntity<MutableList<Academy>>? {
        val academyList : MutableList<Academy> = memberAcademyService.findByMemberId(memberId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(academyList)
    }

    // 학원 아이디로 조회 (학원에 등록된 원생들 조회)
    @GetMapping("/academy/{academyId}")
    fun getMemberAcademyByAcademyId(@PathVariable("academyId") academyId: Long): ResponseEntity<MutableList<Member>>? {
        val memberList : MutableList<Member> = memberAcademyService.findByAcademyId(academyId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(memberList)
    }

    @PostMapping
    fun insertMemberAcademy(@RequestBody memberAcademy: MemberAcademy): MemberAcademy? {
        return memberAcademyService.insertMemberAcademy(memberAcademy)
    }

    @DeleteMapping("/{memberAcademyId}")
    fun deleteMemberAcademy(@PathVariable("memberAcademyId") memberAcademyId: Long) {
        return memberAcademyService.deleteMemberAcademy(memberAcademyId)
    }
}