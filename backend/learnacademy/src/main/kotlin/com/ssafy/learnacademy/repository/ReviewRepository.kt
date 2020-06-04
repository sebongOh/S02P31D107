package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review,Long>{
    fun findByMember(member : Member?) : List<Review>?
    fun findByAcademy(academy : Academy?) : List<Review>?
}