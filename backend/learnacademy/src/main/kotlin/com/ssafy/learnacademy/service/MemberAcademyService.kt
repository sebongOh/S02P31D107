package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.AcademyRepository
import com.ssafy.learnacademy.repository.MemberAcademyRepository
import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.stereotype.Service
import sun.nio.cs.ext.MacThai

@Service
class MemberAcademyService(
        var memberAcademyRepository: MemberAcademyRepository,
        var memberRepository: MemberRepository,
        var academyRepository: AcademyRepository
) {

    fun getMemberAcademy(memberAcademyId: Long): MemberAcademy? {
        return memberAcademyRepository.findById(memberAcademyId).get()
    }

    fun findByMemberId(memberId: Long): MutableList<Academy> {
        val memberAcademyIdList: MutableList<Long> = memberAcademyRepository.findAllByMemberId(memberId)
        var academyList: MutableList<Academy> = mutableListOf()
        if (memberAcademyIdList.size != 0) {
            academyList = academyRepository.findAllByMemberAcademyId(memberAcademyIdList)
        }
        return academyList
    }

    fun findByAcademyId(academyId: Long): MutableList<Member> {
        val memberAcademyIdList: MutableList<Long> = memberAcademyRepository.findAllByAcademyId(academyId)
        var memberList: MutableList<Member> = mutableListOf()
        if (memberAcademyIdList.size != 0) {
            memberList = memberRepository.findAllByMemberAcademyId(memberAcademyIdList)
        }
        return memberList
    }

    fun insertMemberAcademy(memberAcademy: MemberAcademy): MemberAcademy? {
        var memberId: Long = memberAcademy.member?.memberId ?: 0
        var academyId: Long = memberAcademy.academy?.academyId ?: 0
        memberAcademy.member = memberRepository.findById(memberId).get()
        memberAcademy.academy = academyRepository.findById(academyId).get()
        return memberAcademyRepository.save(memberAcademy)
    }

    fun deleteMemberAcademy(memberAcademyId: Long) {
        return memberAcademyRepository.deleteById(memberAcademyId)
    }
}