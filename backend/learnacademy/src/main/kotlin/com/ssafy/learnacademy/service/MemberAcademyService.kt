package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.AcademyRepository
import com.ssafy.learnacademy.repository.MemberAcademyRepository
import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberAcademyService(
        var memberAcademyRepository: MemberAcademyRepository,
        var memberRepository: MemberRepository,
        var academyRepository: AcademyRepository
) {

    fun findAllMemberAcademy() : List<MemberAcademy>?{
        return memberAcademyRepository.findAll()
    }

    fun findByMember(member : Member) : List<MemberAcademy>?{
        return memberAcademyRepository.findByMember(member)
    }

    fun findByAcademy(academy : Academy) : List<MemberAcademy>?{
        return memberAcademyRepository.findByAcademy(academy)
    }

    fun getMemberAcademy(memberAcademyId: Long): MemberAcademy? {
        return memberAcademyRepository.findById(memberAcademyId).get()
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