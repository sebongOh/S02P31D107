package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : CrudRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}
