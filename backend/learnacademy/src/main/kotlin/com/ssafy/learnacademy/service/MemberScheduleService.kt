package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.MemberScheduleRepository
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberSchedule
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberScheduleService(var memberScheduleRepository: MemberScheduleRepository) {

    fun findAll() : List<MemberSchedule>?{
        return memberScheduleRepository.findAll()
    }

    fun findById(memberScheduleId : Long) : Optional<MemberSchedule>?{
        return memberScheduleRepository.findById(memberScheduleId)
    }

    fun insertMemberSchedule(memberSchedule : MemberSchedule) : MemberSchedule?{
        return memberScheduleRepository.save(memberSchedule)
    }

    fun updateMemberSchedule(memberSchedule : MemberSchedule) : MemberSchedule?{
        return memberScheduleRepository.save(memberSchedule)
    }

    fun deleteMemberSchedule(memberSchedule : MemberSchedule){
        return memberScheduleRepository.delete(memberSchedule)
    }
}