package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.Security.JwtTokenProvider
import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.Member
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
class MemberService
(val memberRepository: MemberRepository,
 val jwtTokenProvider: JwtTokenProvider,
 val bcryptPasswordEncoder: BCryptPasswordEncoder) {



    fun findById(memberId: Long): Member? {
        return memberRepository.findByIdOrNull(memberId)
    }

    fun findByEmail(email: String): Member? {
        return memberRepository.findByEmail(email)
    }

    fun insertMember(member: Member): Member? {
        member.password = BCryptPasswordEncoder().encode(member.password)
        member.profileUrl = ""
        return memberRepository.save(member)
    }

    fun updateMember(member: Member): Member? {
        member.password = BCryptPasswordEncoder().encode(member.password)
        return memberRepository.save(member)
    }

    fun deleteMember(memberId: Long) {
        return memberRepository.deleteById(memberId)
    }

    fun signIn(loginMember: Member, password: String): String {
        if (!bcryptPasswordEncoder.matches(password, loginMember.password))
            throw IllegalArgumentException()
        return jwtTokenProvider.createToken(loginMember.memberId.toString(), loginMember.roles)
    }
}