package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberAcademyRepository : JpaRepository<MemberAcademy, Long> {

    @Query("SELECT ma.memberAcademyId FROM MemberAcademy ma INNER JOIN Member m ON ma.member.memberId = m.memberId WHERE ma.member.memberId = ?1")
    fun findAllByMemberId(memberId: Long) : MutableList<Long>

    @Query("SELECT ma.memberAcademyId FROM MemberAcademy ma INNER JOIN Academy a ON ma.academy.academyId = a.academyId WHERE ma.academy.academyId = ?1")
    fun findAllByAcademyId(memberId: Long) : MutableList<Long>

}
