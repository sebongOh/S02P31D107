package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.Member
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberService(var memberRepository: MemberRepository) {

    fun findById(memberId: Int): Optional<Member>? {
        return memberRepository.findById(memberId)
    }

    fun insertMember(member: Member): Member? {
        return memberRepository.save(member)
    }

    fun updateMember(member: Member): Member? {
        return memberRepository.save(member)
    }

    fun deleteMember(memberId: Int) {
        return memberRepository.deleteById(memberId)
    }
}