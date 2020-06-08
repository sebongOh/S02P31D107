package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.AcademySchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AcademyScheduleRepository : JpaRepository<AcademySchedule,Long> {

    @Query("SELECT ass FROM AcademySchedule ass INNER JOIN Academy a ON ass.academy.academyId = a.academyId WHERE a.academyId = ?1")
    fun findByAcademyId(academyId: Long): MutableList<AcademySchedule>
}