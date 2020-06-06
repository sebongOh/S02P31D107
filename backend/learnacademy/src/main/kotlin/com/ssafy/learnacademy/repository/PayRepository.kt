package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.AcademySchedule
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Pay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PayRepository : JpaRepository<Pay, Long>{
    fun findByMember(member : Member?) : List<Pay>?
    fun findByAcademySchedule(academySchedule : AcademySchedule) : List<Pay>?
    fun findByTid(tid : String) : Pay?

    @Query("select p from Pay p where p.academySchedule.academyScheduleId = (select a.academyScheduleId from AcademySchedule a where a.academy.academyId = :academy_id)")
    fun findByAcademyId(@Param("academy_id") academyId: Long) : List<Pay>?

    @Query("select p from Pay p where p.academySchedule.academyScheduleId=:academy_schedule_id and p.member.memberId = :member_id")
    fun getMyPay(@Param("member_id") memberId : Long,@Param("academy_schedule_id") scheduleId : Long) : List<Pay>?
}