package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.VerifyRepository
import com.ssafy.learnacademy.vo.Verify
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.pow

@Service
class VerifyService(val verifyRepository: VerifyRepository, val mailSender: JavaMailSender) {

    fun findById(verifyId: Long): Verify? {
        return verifyRepository.findById(verifyId).get()
    }

    fun findByEmail(email: String): Verify? {
        return verifyRepository.findByEmail(email)
    }

    fun insertVerify(verify: Verify): Verify? {
        return verifyRepository.save(verify)
    }

    fun updateVerify(verify: Verify): Verify? {
        return verifyRepository.save(verify)
    }

    fun deleteVerify(verifyId: Long) {
        return verifyRepository.deleteById(verifyId)
    }

    fun sendEmail(verify: Verify) {
        val message = SimpleMailMessage()
        message.setTo(verify.email)
        message.setSubject("[배울학] 이메일 인증 코드 발송 메일입니다.") // 제목
        val contents = "회원가입 페이지에서 인증 코드를 입력하세요.\n\ncode : " + verify.code
        message.setText(contents)
        mailSender.send((message))
    }

    fun randomCode(): String? {
        val certNumLength: Int = 6

        val random: Random = Random(System.currentTimeMillis())
        val range = 10.0.pow(certNumLength.toDouble()).toInt()
        val trim = 10.0.pow(certNumLength - 1.toDouble()).toInt()

        var result = random.nextInt(range) + trim
        if (result > range) {
            result -= trim
        }
        return result.toString()
    }
}