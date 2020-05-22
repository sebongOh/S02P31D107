package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.Member
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(var memberRepository: MemberRepository) {

    fun getMember(memberId: Long): Member? {
        return memberRepository.findByIdOrNull(memberId)
    }

    fun getMemberByEmail(email: String): Member? {
        return memberRepository.findByEmail(email)
    }

    fun insertMember(member: Member): Member? {
        return memberRepository.save(member)
    }

    fun updateMember(member: Member): Member? {
        return memberRepository.save(member)
    }

    fun deleteMember(memberId: Long) {
        return memberRepository.deleteById(memberId)
    }

}