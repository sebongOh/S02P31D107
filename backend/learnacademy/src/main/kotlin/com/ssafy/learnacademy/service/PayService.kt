package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.PayRepository
import com.ssafy.learnacademy.vo.AcademySchedule
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Pay
import org.springframework.stereotype.Service
import java.util.*

@Service
class PayService (var payRepository: PayRepository){
    fun findAll() : List<Pay>?{
        return payRepository.findAll()
    }

    fun findById(payId : Long) : Pay?{
        return payRepository.findById(payId).get()
    }

    fun insertPay(pay: Pay) : Pay?{
        return payRepository.save(pay)
    }

    fun updatePay(pay: Pay) : Pay?{
        return payRepository.save(pay)
    }

    fun deletePay(payId : Long){
        return payRepository.deleteById(payId)
    }

    fun findByMember(member : Member?) : List<Pay>?{
        return payRepository.findByMember(member)
    }

    fun findBySchedule(schedule : AcademySchedule) : List<Pay>?{
        return payRepository.findByAcademySchedule(schedule)
    }

    fun findByAcademyId(academyId : Long) : List<Pay>?{
        return payRepository.findByAcademyId(academyId)
    }

    fun findByTid(tid : String) : Pay?{
        return payRepository.findByTid(tid)
    }

    fun getMyPay(memberId : Long, scheduleId : Long) : List<Pay>?{
        return payRepository.getMyPay(memberId,scheduleId)
    }
}