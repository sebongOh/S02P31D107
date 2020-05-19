package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.MemberScheduleRepository
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberSchedule
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberScheduleService(var memberScheduleRepository: MemberScheduleRepository) {

    fun findById(memberScheduleId : Int) : Optional<MemberSchedule>?{
        return memberScheduleRepository.findById(memberScheduleId)
    }

    fun insertMemberSchedule(memberSchedule : MemberSchedule) : MemberSchedule?{
        return memberScheduleRepository.save(memberSchedule)
    }

    fun updateMemberSchedule(memberSchedule : MemberSchedule) : MemberSchedule?{
        return memberScheduleRepository.save(memberSchedule)
    }

    fun deleteMemberSchedule(memberScheduleId : Int){
        return memberScheduleRepository.deleteById(memberScheduleId)
    }
}