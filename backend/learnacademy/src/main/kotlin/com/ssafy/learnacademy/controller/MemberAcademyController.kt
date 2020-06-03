package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.service.MemberAcademyService
import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberAcademy
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/memberacademy")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class MemberAcademyController (var memberAcademyService: MemberAcademyService,
                                var academyService: AcademyService,
                                var memberService: MemberService) {

    @GetMapping("/")
    @ApiOperation(value = "모든 학원회원 검색",notes = "모든 학원회원들을 검색합니다")
    fun getAllMemberAcademy() : ResponseEntity<List<MemberAcademy>>?{
        val memberAcademy : List<MemberAcademy>? = memberAcademyService.findAllMemberAcademy() ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(memberAcademy)
    }

    @GetMapping("/{memberAcademyId}")
    @ApiOperation(value = "학원을 등록한 회원 검색", notes = "학원을 등록한 회원을 검색합니다")
    fun getMemberAcademy(@PathVariable("memberAcademyId") memberAcademyId: Long): ResponseEntity<MemberAcademy>? {
        val memberAcademy : MemberAcademy? = memberAcademyService.getMemberAcademy(memberAcademyId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(memberAcademy)
    }

    @GetMapping("/{academyId}/academy")
    @ApiOperation(value = "학원의 회원 검색", notes = "학원의 회원들을 검색합니다")
    fun findByAcademy(@PathVariable("academyId") academyId : Long ) : ResponseEntity<List<MemberAcademy>>?{
        val academy : Academy? = academyService.getAcademy(academyId) ?: return ResponseEntity.noContent().build()
        val memberAcademy : List<MemberAcademy>? = memberAcademyService.findByAcademy(academy!!) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(memberAcademy)
    }

    @GetMapping("/{memberId}/member")
    @ApiOperation(value = "회원이 등록한 학원 검색", notes = "회원이 등록한 학원을 검색합니다")
    fun findByMember(@PathVariable("memberId") memberId : Long ) : ResponseEntity<List<MemberAcademy>>{
        val member : Member? = memberService.findById(memberId) ?: return ResponseEntity.noContent().build()
        val memberAcademy : List<MemberAcademy>? = memberAcademyService.findByMember(member!!) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(memberAcademy)
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