package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.AcademyRepository
import com.ssafy.learnacademy.repository.MemberAcademyRepository
import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberAcademyService(
        var memberAcademyRepository: MemberAcademyRepository,
        var memberRepository: MemberRepository,
        var academyRepository: AcademyRepository
) {

    fun getMemberAcademy(memberAcademyId: Long): Optional<MemberAcademy>? {
        return memberAcademyRepository.findById(memberAcademyId)
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