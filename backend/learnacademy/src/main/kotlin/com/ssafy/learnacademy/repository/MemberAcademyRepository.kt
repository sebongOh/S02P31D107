package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.MemberAcademy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberAcademyRepository : JpaRepository<MemberAcademy, Long>
