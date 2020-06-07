package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.service.VerifyService
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Verify
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/verify")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class VerifyController(val verifyService: VerifyService, val memberService: MemberService) {

    @PostMapping("/sendEmail")
    @ApiOperation(value = "인증 코드 이메일로 발송", notes = "인증 코드를 입력한 이메일로 발송합니다.")
    fun sendEmail(@RequestBody verify: Verify) : ResponseEntity<Unit> {
        val member: Member? = verify.email?.let { memberService.findByEmail(it) }
        if (member != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
        val isVerify: Verify? = verify.email?.let { verifyService.findByEmail(it) }
        verify.code = verifyService.randomCode()
        if (isVerify == null) {
            verifyService.insertVerify(verify)
        } else {
            isVerify.code = verify.code
            verifyService.updateVerify(isVerify)
        }
        
        verifyService.sendEmail(verify)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/checkCode")
    @ApiOperation(value = "이메일 코드 인증", notes = "이메일로 날아온 코드를 입력하여 이메일을 인증합니다.")
    fun checkCode(@RequestBody verify: Verify) : ResponseEntity<Unit> {
        val isVerify: Verify? = verifyService.findByEmail(verify.email ?: "")
                ?: return ResponseEntity.notFound().build()
        if (isVerify != null && verify.code == isVerify.code) {
            isVerify.code = "Y"
            verifyService.updateVerify(isVerify)
        } else return ResponseEntity.badRequest().build()
        return ResponseEntity.ok().build()
    }
}