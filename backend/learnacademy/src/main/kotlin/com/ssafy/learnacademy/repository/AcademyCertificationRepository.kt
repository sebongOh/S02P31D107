package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.AcademyCertification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AcademyCertificationRepository: JpaRepository<AcademyCertification, Long>