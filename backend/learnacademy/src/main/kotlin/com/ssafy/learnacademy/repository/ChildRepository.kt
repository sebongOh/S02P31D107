package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Child
import com.ssafy.learnacademy.vo.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChildRepository : JpaRepository<Child,Long>{
    fun findByMember(member : Member) : List<Child>
}