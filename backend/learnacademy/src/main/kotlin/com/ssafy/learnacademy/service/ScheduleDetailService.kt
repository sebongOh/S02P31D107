package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.ScheduleDetailRepository
import com.ssafy.learnacademy.vo.AcademySchedule
import com.ssafy.learnacademy.vo.ScheduleDetail
import org.springframework.stereotype.Service
import java.util.*

@Service
class ScheduleDetailService(var scheduleDetailRepository: ScheduleDetailRepository) {

    fun findAll() : List<ScheduleDetail>?{
        return scheduleDetailRepository.findAll()
    }

    fun findById(scheduleDetailId : Long) : ScheduleDetail?{
        return scheduleDetailRepository.findById(scheduleDetailId).get()
    }

    fun findByAcademySchedule(academySchedule: AcademySchedule) : List<ScheduleDetail>?{
        return scheduleDetailRepository.findByAcademySchedule(academySchedule)
    }

    fun insertScheduleDetail(scheduleDetail: ScheduleDetail) : ScheduleDetail? {
        return scheduleDetailRepository.save(scheduleDetail)
    }

    fun updateScheduleDetail(scheduleDetail: ScheduleDetail) : ScheduleDetail? {
        return scheduleDetailRepository.save(scheduleDetail)
    }

    fun deleteScheduleDetail(scheduleDetail: ScheduleDetail){
        return scheduleDetailRepository.delete(scheduleDetail)
    }
}