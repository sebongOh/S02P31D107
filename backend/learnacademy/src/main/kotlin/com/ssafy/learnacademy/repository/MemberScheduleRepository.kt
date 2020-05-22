package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.MemberSchedule
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberScheduleRepository : CrudRepository<MemberSchedule,Int>