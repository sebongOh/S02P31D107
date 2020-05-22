package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Academy
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AcademyRepository : JpaRepository<Academy, Long>
