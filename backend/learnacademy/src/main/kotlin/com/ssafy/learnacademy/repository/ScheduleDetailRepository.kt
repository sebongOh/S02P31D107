package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.ScheduleDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScheduleDetailRepository : JpaRepository<ScheduleDetail,Long>