package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.Member
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(userPk: String): UserDetails {
        var member: Member = memberRepository.findById(userPk.toLong()).get()
        return member as UserDetails
    }
}
