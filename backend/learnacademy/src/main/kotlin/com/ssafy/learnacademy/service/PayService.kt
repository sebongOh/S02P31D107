package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.PayRepository
import com.ssafy.learnacademy.vo.Pay
import org.springframework.stereotype.Service
import java.util.*

@Service
class PayService (var payRepository: PayRepository){
    fun findAll() : Iterable<Pay>?{
        return payRepository.findAll()
    }

    fun findById(payId : Long) : Optional<Pay>?{
        return payRepository.findById(payId)
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
}