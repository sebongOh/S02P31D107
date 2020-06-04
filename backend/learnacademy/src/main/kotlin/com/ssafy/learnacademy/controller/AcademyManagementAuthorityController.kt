package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyManagementAuthorityService
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.AcademyManagementAuthority
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/academy-management-authority")
//@CrossOrigin(origins = ["*"], maxAge = 3600)
class AcademyManagementAuthorityController(
        val academyManagementAuthorityService: AcademyManagementAuthorityService
) {

    @GetMapping("/{academyManagementAuthorityId}")
    fun getAcademyManagementAuthority(@PathVariable("academyManagementAuthorityId") AcademyManagementAuthorityId: Long): ResponseEntity<AcademyManagementAuthority>? {
        val academyManagementAuthority = academyManagementAuthorityService.findById(AcademyManagementAuthorityId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(academyManagementAuthority)
    }

    // 학원 회원 아이디로 조회 (학원 회원이 관리할 수 있는 학원들 조회)
    @GetMapping("/member/{memberId}")
    fun getAcademyManagementAuthorityByMemberId(@PathVariable("memberId") memberId: Long): ResponseEntity<MutableList<Academy>>? {
        val academyList : MutableList<Academy> = academyManagementAuthorityService.findByMemberId(memberId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(academyList)
    }

    // 학원 아이디로 학원 관리권한을 가지고 있는 회원 조회 (학원 권한을 가지고 있는 회원들 조회)
    @GetMapping("/academy/{academyId}")
    fun getAcademyManagementAuthorityByAcademyId(@PathVariable("academyId") academyId: Long): ResponseEntity<MutableList<Member>>? {
        val memberList : MutableList<Member> = academyManagementAuthorityService.findByAcademyId(academyId) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().body(memberList)
    }

    @PostMapping
    fun insertMemberAcademy(@RequestBody academyManagementAuthority: AcademyManagementAuthority): ResponseEntity<AcademyManagementAuthority>? {
        val insertAcademyManagementAuthority: AcademyManagementAuthority = academyManagementAuthorityService.insert(academyManagementAuthority)
        return ResponseEntity.ok().body(insertAcademyManagementAuthority)
    }

    @DeleteMapping("/{academyManagementAuthorityId}")
    fun deleteMemberAcademy(@PathVariable("academyManagementAuthorityId") academyManagementAuthorityId: Long) {
        return academyManagementAuthorityService.delete(academyManagementAuthorityId)
    }
}