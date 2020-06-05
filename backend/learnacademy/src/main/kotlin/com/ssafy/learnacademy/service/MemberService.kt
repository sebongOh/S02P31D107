package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.Security.JwtTokenProvider
import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.Member
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class MemberService
(val memberRepository: MemberRepository,
 val jwtTokenProvider: JwtTokenProvider,
 val bcryptPasswordEncoder: BCryptPasswordEncoder,
 val mailSender: JavaMailSender) {

    fun findById(memberId: Long): Member? {
        return memberRepository.findById(memberId).get()
    }

    fun findByEmail(email: String): Member? {
        return memberRepository.findByEmail(email)
    }

    fun insertMember(member: Member): Member? {
        member.password = BCryptPasswordEncoder().encode(member.password)
        return memberRepository.save(member)
    }

    fun updateMember(member: Member): Member? {
        member.password = BCryptPasswordEncoder().encode(member.password)
        return memberRepository.save(member)
    }

    fun deleteMember(member: Member) {
        return memberRepository.delete(member)
    }

    fun signIn(loginMember: Member, password: String): String {
        if (!bcryptPasswordEncoder.matches(password, loginMember.password))
            throw IllegalArgumentException()
        return jwtTokenProvider.createToken(loginMember.memberId.toString(), loginMember.roles)
    }

    fun sendTempPassword(email: String, password: String) {
        val message = SimpleMailMessage()
        message.setTo(email)
        message.setSubject("[배울학] 임시 비밀번호 발급 메일입니다.") // 제목
        val contents = "임시 비밀번호로 로그인 후 회원 정보 수정 페이지에서 비밀번호를 변경하세요.\n\n임시 비밀번호 : " + password
        message.setText(contents)
        mailSender.send((message))
    }

    fun randomPassword(): String {
        var tempPassword :String = ""
        val random: Random = Random()
        var num: Int = 0
        for (i in 1..10) {
            num = random.nextInt(3)
            when (num) {
                0 -> tempPassword += (random.nextInt(26) + 97).toChar()
                1 -> tempPassword += (random.nextInt(26) + 65).toChar()
                2 -> tempPassword += random.nextInt(10)
            }
        }
        return tempPassword
    }

    fun getMember(): Member? {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val member: Member = authentication.getPrincipal() as Member
        return memberRepository.findByEmail(member.email ?: "")
    }

}