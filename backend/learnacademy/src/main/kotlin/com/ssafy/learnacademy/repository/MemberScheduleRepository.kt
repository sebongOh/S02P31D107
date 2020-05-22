package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.MemberSchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberScheduleRepository : JpaRepository<MemberSchedule, Long>