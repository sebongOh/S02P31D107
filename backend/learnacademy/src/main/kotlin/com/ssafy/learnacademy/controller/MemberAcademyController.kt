package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberAcademyService
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/memberacademy")
class MemberAcademyController (var memberAcademyService: MemberAcademyService) {

    @GetMapping("/{memberAcademyId}")
    fun getMemberAcademy(@PathVariable("memberAcademyId") memberAcademyId: Int): Optional<MemberAcademy>? {
        return memberAcademyService.getMemberAcademy(memberAcademyId)
    }

    // 일반 회원 아이디로 조회 (일반 회원이 등록한 학원들 조회)

    // 학원 아이디로 조회 (학원에 등록된 원생들 조회)

    @PostMapping
    fun insertMemberAcademy(@RequestBody memberAcademy: MemberAcademy): MemberAcademy? {
        return memberAcademyService.insertMemberAcademy(memberAcademy)
    }

    @DeleteMapping("/{memberAcademyId}")
    fun deleteMemberAcademy(@PathVariable("memberAcademyId") memberAcademyId: Int) {
        return memberAcademyService.deleteMemberAcademy(memberAcademyId)
    }
}