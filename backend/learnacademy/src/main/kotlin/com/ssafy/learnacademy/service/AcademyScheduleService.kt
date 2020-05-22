package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.AcademyScheduleRepository
import com.ssafy.learnacademy.vo.AcademySchedule
import org.springframework.stereotype.Service
import java.util.*

@Service
class AcademyScheduleService (var academyScheduleRepository: AcademyScheduleRepository){

    fun findAll() : List<AcademySchedule>?{
        return academyScheduleRepository.findAll()
    }

    fun findById(academyScheduleId : Long): Optional<AcademySchedule>?{
        return academyScheduleRepository.findById(academyScheduleId)
    }

    fun insertAcademySchedule(academySchedule: AcademySchedule) : AcademySchedule?{
        return academyScheduleRepository.save(academySchedule)
    }

    fun updateAcademySchedule(academySchedule: AcademySchedule) : AcademySchedule?{
        return academyScheduleRepository.save(academySchedule)
    }

    fun deleteAcademySchedule(academySchedule : AcademySchedule){
        return academyScheduleRepository.delete(academySchedule)
    }

}