package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.AcademySchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AcademyScheduleRepository : JpaRepository<AcademySchedule,Long>