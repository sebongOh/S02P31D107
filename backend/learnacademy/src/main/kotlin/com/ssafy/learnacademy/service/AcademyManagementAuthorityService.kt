package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.AcademyManagementAuthorityRepository
import com.ssafy.learnacademy.repository.AcademyRepository
import com.ssafy.learnacademy.repository.MemberRepository
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.AcademyCertification
import com.ssafy.learnacademy.vo.AcademyManagementAuthority
import com.ssafy.learnacademy.vo.Member
import org.springframework.stereotype.Service

@Service
class AcademyManagementAuthorityService (
        val academyManagementAuthorityRepository: AcademyManagementAuthorityRepository,
        val memberRepository: MemberRepository,
        val academyRepository: AcademyRepository

        ) {

    fun findByMemberId(memberId: Long): MutableList<Academy> {
        val memberAcademyIdList: MutableList<Long> = academyManagementAuthorityRepository.findAllByMemberId(memberId)
        val academyList: MutableList<Academy> = academyRepository.findAllByMemberAcademyId(memberAcademyIdList)
        return academyList
    }

    fun findByAcademyId(academyId: Long): MutableList<Member> {
        val memberAcademyIdList: MutableList<Long> = academyManagementAuthorityRepository.findAllByAcademyId(academyId)
        val memberList: MutableList<Member> = memberRepository.findAllByMemberAcademyId(memberAcademyIdList)
        return memberList
    }
    fun findAll(): MutableList<AcademyManagementAuthority> {
        return academyManagementAuthorityRepository.findAll()
    }

    fun findById(academyManagementAuthorityId: Long): AcademyManagementAuthority {
        return academyManagementAuthorityRepository.findById(academyManagementAuthorityId).get()
    }

    fun insert(academyManagementAuthority: AcademyManagementAuthority): AcademyManagementAuthority {
        return academyManagementAuthorityRepository.save(academyManagementAuthority)
    }

    fun delete(academyManagementAuthorityId: Long) {
        return academyManagementAuthorityRepository.deleteById(academyManagementAuthorityId)
    }
}