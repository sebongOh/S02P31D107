package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Verify
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VerifyRepository : CrudRepository<Verify, Long> {
    fun findByEmail(email: String): Verify?
}