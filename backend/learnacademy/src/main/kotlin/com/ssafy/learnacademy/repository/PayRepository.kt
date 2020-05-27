package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Pay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PayRepository : JpaRepository<Pay, Long>{
    fun findByMember(member : Member?) : List<Pay>?
}