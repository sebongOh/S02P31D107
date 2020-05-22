package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.PayService
import com.ssafy.learnacademy.vo.Pay
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/pay")
class PayController(var payService: PayService) {

    @GetMapping
    @ApiOperation(value = "결제내역 전체검색", notes = "결제내역을 전부 불러옵니다")
    fun getAllPay() = payService.findAll()

    @GetMapping("/{payId}")
    @ApiOperation(value="결제내역 검색",notes = "결제내역을 검색합니다")
    fun getPay(@PathVariable("payId") payId : Long) : Optional<Pay>?{
        return payService.findById(payId)
    }

    @PostMapping
    @ApiOperation(value="결제내역 등록",notes = "결제내역을 등록합니다")
    fun insertPay(@RequestBody pay : Pay) : Pay?{
        return payService.insertPay(pay)
    }

    @PutMapping
    @ApiOperation(value="결제내역 수정",notes = "결제내역을 수정합니다")
    fun updatePay(@RequestBody pay: Pay) : Pay?{
        return payService.updatePay(pay)
    }

    @DeleteMapping("/{payId}")
    @ApiOperation(value = "결제내역 삭제", notes = "결제내역을 삭제합니다")
    fun deletePay(@PathVariable("payId") payId : Long){
        return payService.deletePay(payId)
    }
}