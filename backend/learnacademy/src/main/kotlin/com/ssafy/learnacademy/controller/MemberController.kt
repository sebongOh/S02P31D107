package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyCertificationService
import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.service.S3UploadService
import com.ssafy.learnacademy.vo.*
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class MemberController(
        val memberService: MemberService,
        val academyService: AcademyService,
        val academyCertificationService: AcademyCertificationService,
        val s3UploadService: S3UploadService
) {

    @GetMapping()
    @ApiOperation(value = "멤버 찾기", notes = "멤버를 검색합니다")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun getMember(): ResponseEntity<Member> {
        val member: Member? = memberService.getMember() ?: return ResponseEntity.noContent().build()
        member?.password = ""
        return ResponseEntity.ok().body(member)
    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원 가입", notes = "회원 정보를 등록합니다. 이때 json 형식이 아닌 form-data형식으로, multipart id를 profileFile로 보내주세요.")
    fun insertMember(member: Member): ResponseEntity<Member> {
        member.type = "일반회원"
        if (member.profileFile != null) {
            member.profileUrl = s3UploadService.uploadFile(member.profileFile, "profile/")
        } else {
            member.profileUrl = "https://learnacademy.s3.ap-northeast-2.amazonaws.com/profile/default.png"
        }
        member.roles.add("ROLE_USER")
        val insertMember :Member? = memberService.insertMember(member)
        return ResponseEntity.ok().body(insertMember)
    }

    @PostMapping("/academy-signup")
    @ApiOperation(value = "학원 회원 가입", notes = "학원 회원 정보를 등록합니다. 회원 가입 데이터 + academyId, imageFile 추가하여 보내주세요.")
    fun insertAcademyMember(academyCertificationRequest: AcademyCertificationRequest): ResponseEntity<Member> {
        val member: Member = Member()
        member.email = academyCertificationRequest.email
        member.setPassword(academyCertificationRequest.password)
        member.name = academyCertificationRequest.name
        member.address = academyCertificationRequest.address
        member.phone = academyCertificationRequest.phone
        member.age = academyCertificationRequest.age
        member.gender = academyCertificationRequest.gender
        member.profileFile = academyCertificationRequest.profileFile
        member.roles.add("ROLE_ADMIN")
        member.type = "학원회원"
        if (academyCertificationRequest.profileFile != null) {
            member.profileUrl = s3UploadService.uploadFile(academyCertificationRequest.profileFile, "profile/")
        } else {
            member.profileUrl = "https://learnacademy.s3.ap-northeast-2.amazonaws.com/profile/default.png"
        }
        val insertMember: Member? = memberService.insertMember(member)
        val academyCertification: AcademyCertification = AcademyCertification()
        academyCertification.member = insertMember
        val academy: Academy = Academy()
        try {
            val findAcademy: Academy? = academyService.getAcademy(academyCertificationRequest.academyId ?: 0)
            academyCertification.academy = findAcademy
        } catch (e: Exception) {
            academy.academyId = academyCertificationRequest.academyId
            academy.contents = "학원 소개가 없습니다."
            academy.category = ""
            academy.phone = academyCertificationRequest.academyPhone
            academy.name = academyCertificationRequest.academyName
            academy.address = academyCertificationRequest.academyAddress
            academy.category = academyCertificationRequest.academyCategory
            academy.imageUrl = "https://learnacademy.s3.ap-northeast-2.amazonaws.com/academy/default.jpg"
            academyCertification.academy = academyService.insertAcademy(academy)
        }

        if (academyCertificationRequest.imageFile != null) {
            academyCertification.imageUrl = s3UploadService.uploadFile(academyCertificationRequest.imageFile, "cert/")
        } else {
            academyCertification.imageUrl = "https://learnacademy.s3.ap-northeast-2.amazonaws.com/cert/default.jpg"
        }
        val insertAcademyCertification = academyCertificationService.insert(academyCertification)
        academyCertificationService.sendCertificationInfoToAdmin(insertAcademyCertification)
        return ResponseEntity.ok().body(insertMember)
    }

    @PutMapping
    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정합니다. 이때 json 형식이 아닌 form-data형식으로, multipart id를 profileFile로 보내주세요. " +
            "\n비밀번호는 정부 수정 시 재입력해서 수정폼에 들어오도록 해주세요.")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun updateMember(@RequestBody member: Member): ResponseEntity<Member> {
        val findMember: Member? = memberService.getMember()
        member.memberId = findMember?.memberId
        if (member.profileFile != null) {
            member.profileUrl = s3UploadService.uploadFile(member.profileFile, "profile/")
        }
        val insertMember :Member? = memberService.updateMember(member)
        return ResponseEntity.ok().body(insertMember)
    }

    @DeleteMapping
    @ApiOperation(value = "회원 정보 삭제(탈퇴)", notes = "회원 정보를 삭제합니다.")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun deleteMember(): ResponseEntity<Unit> {
        try {
            val findMember: Member = memberService.getMember() ?: return ResponseEntity.notFound().build()
            memberService.deleteMember(findMember)
        } catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok().build()
    }

    @PostMapping("/signin")
    @ApiOperation(value = "로그인", notes = "이메일, 비밀번호를 받고 정보 일치 시 token을 발급합니다.")
    fun signIn(@RequestBody member: MemberRequest): ResponseEntity<String>? {
        val loginMember: Member = memberService.findByEmail(member.email ?:"")
                ?: return ResponseEntity.notFound().build()
        if (!loginMember.type.equals(member.type)) return ResponseEntity.badRequest().build()
        val access_token: String = memberService.signIn(loginMember, member.password ?: "")
        return ResponseEntity.ok().body(access_token)
    }

    @PostMapping("/checkPassword")
    @ApiOperation(value = "비밀번호 확인", notes = "비밀번호가 맞는지 확인합니다. 이때 이메일과 비밀번호를 json 형식으로 날려주세요.")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun checkPassword(@RequestBody member: MemberRequest): ResponseEntity<Unit>? {
        var findMember: Member = memberService.findByEmail(member.email ?:"") ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().build()
    }

    @PostMapping("/findPassword")
    @ApiOperation(value = "비밀번호 찾기", notes = "비밀번호를 찾습니다. email과 name을 json 형식으로 날려주세요.")
    fun findPassword(@RequestBody member: Member): ResponseEntity<Unit>? {
        var findMember: Member = memberService.findByEmail(member.email ?:"")
                ?: return ResponseEntity.notFound().build()
        if (!findMember.name.equals(member.name)) return ResponseEntity.notFound().build()
        val tempPassword = memberService.randomPassword()
        findMember.password = tempPassword
        memberService.updateMember(findMember)
        memberService.sendTempPassword(member.email ?: "", tempPassword)
        return ResponseEntity.ok().build()
    }

}