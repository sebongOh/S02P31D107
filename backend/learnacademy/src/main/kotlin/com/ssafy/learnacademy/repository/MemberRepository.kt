package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?

    @Query("SELECT m FROM MemberAcademy ma INNER JOIN Member m ON ma.member.memberId = m.memberId WHERE m.memberId IN ?1")
    fun findAllByMemberAcademyId(memberAcademy: MutableList<Long>): MutableList<Member>
}
