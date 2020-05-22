package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.service.VerifyService
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Verify
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/verify")
class verifyController(var verifyService: VerifyService, var memberService: MemberService) {

    @PostMapping
    fun sendEmail(@RequestBody verify: Verify) : ResponseEntity<Unit> {
        println(verify.email)
        val member: Member? = verify.email?.let { memberService.getMemberByEmail(it) }
        if (member != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
        val isVerify: Verify? = verify.email?.let { verifyService.getVerifyByEmail(it) }
        if (isVerify == null) {
            verify.code = verifyService.randomCode()
            verifyService.insertVerify(verify)
        } else {
            isVerify.code = verifyService.randomCode()
            verifyService.updateVerify(isVerify)
        }
        
        verifyService.sendEmail(verify)
        return ResponseEntity.ok().build()
    }
}