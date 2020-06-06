package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AcademyRepository : JpaRepository<Academy, Long> {

    @Query("SELECT a FROM MemberAcademy ma INNER JOIN Academy a ON ma.academy.academyId = a.academyId WHERE ma.memberAcademyId IN ?1")
    fun findAllByMemberAcademyId(memberAcademy: MutableList<Long>): MutableList<Academy>

    @Query("SELECT a FROM AcademyManagementAuthority ama INNER JOIN Academy a ON ama.academy.academyId = a.academyId WHERE ama.academyManagementAuthorityId IN ?1")
    fun findAllByAcademyManagementId(memberAcademy: MutableList<Long>): MutableList<Academy>
}
