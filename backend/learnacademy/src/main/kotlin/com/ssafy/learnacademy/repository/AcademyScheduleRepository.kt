package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.AcademySchedule
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AcademyScheduleRepository : CrudRepository<AcademySchedule,Long>