package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.AcademyManagementAuthority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AcademyManagementAuthorityRepository: JpaRepository<AcademyManagementAuthority, Long> {
    @Query("SELECT ama.academyManagementAuthorityId FROM AcademyManagementAuthority ama INNER JOIN Member m ON ama.member.memberId = m.memberId WHERE ama.member.memberId = ?1")
    fun findAllByMemberId(memberId: Long) : MutableList<Long>

    @Query("SELECT ama.academyManagementAuthorityId FROM AcademyManagementAuthority ama INNER JOIN Academy a ON ama.academy.academyId = a.academyId WHERE ama.academy.academyId = ?1")
    fun findAllByAcademyId(memberId: Long) : MutableList<Long>
}