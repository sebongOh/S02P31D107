package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberAcademyRepository : JpaRepository<MemberAcademy, Long>{
    fun findByMember(member: Member) : List<MemberAcademy>?
    fun findByAcademy(academy : Academy) : List<MemberAcademy>?
}
