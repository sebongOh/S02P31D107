package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyScheduleService
import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.service.PayService
import com.ssafy.learnacademy.vo.*
import io.swagger.annotations.ApiOperation
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.net.URI
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/pay")
class PayController(var payService: PayService, var memberService: MemberService, var academyScheduleService: AcademyScheduleService) {

    final var host : String = "https://kapi.kakao.com"
    var kakaoPayApproval: KakaoPayApproval? =null
    var kakaoPayReady: KakaoPayReady? = null

    @PostMapping("/ready")
    fun kakaoPayReady(@RequestBody request : HttpServletRequest, pay : Pay) : ResponseEntity<String>{
        var headers = HttpHeaders()
        var restTemplate = RestTemplate()
        val webUrl : String = "http://localhost:8080/"

        headers.add("Authorization","KakaoAK "+"e49189dc92bf26fc3e7d020bf2ac50a3")
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8")

        var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("cid","TC0ONETIME")
        params.add("partner_order_id", "HAK Corp.")
        params.add("partner_user_id", pay.academySchedule?.academy?.name)
        params.add("item_name", pay.academySchedule?.name)
        params.add("quantity", "1")
        params.add("total_amount", pay?.academySchedule?.price.toString())
        params.add("tax_free_amount","0")
        params.add("approval_url", webUrl+"paySuccess?memberId="+pay.member?.name+"&academyScheduleId="+pay.academySchedule?.name)
        params.add("cancel_url",webUrl+"home")
        params.add("fail_url",webUrl+"404")

        var body : HttpEntity<MultiValueMap<String,String>> = HttpEntity<MultiValueMap<String,String>>(params,headers)
        try {
            kakaoPayReady = restTemplate.postForObject(URI(host + "v1/payment/ready"), body, KakaoPayReady::class.java)
            return ResponseEntity(kakaoPayReady!!.next_redirect_pc_url, HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/success/{memberId}/{academyScheduleId}/{pg_token}")
    fun kakaoPayInfo(@PathVariable memberId : String, @PathVariable academyScheduleId : String, @PathVariable pg_token : String ) : ResponseEntity<Pay>{
        var restTemplate = RestTemplate()
        var headers = HttpHeaders()

        headers.add("Authorization","KakaoAK "+"e49189dc92bf26fc3e7d020bf2ac50a3")
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8")

        var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReady?.tid);
        params.add("partner_order_id", "HAK Corp.");
        params.add("partner_user_id", memberId);
        params.add("pg_token", pg_token);

        var body : HttpEntity<MultiValueMap<String,String>> = HttpEntity<MultiValueMap<String,String>>(params,headers)

        try {
            kakaoPayApproval = restTemplate.postForObject(URI(host.toString() + "v1/payment/approve"), body,
                    KakaoPayApproval::class.java)
            val pay: Pay = createPay(kakaoPayApproval, academyScheduleId.toLong(), memberId.toLong())
            insertPay(pay)
            return ResponseEntity<Pay>(pay, HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    fun createPay(kakaoPayApproval: KakaoPayApproval?, academyScheduleId: Long, memberId: Long) : Pay{

        val pay = Pay()
        pay.member = memberService.findById(memberId)
        pay.academySchedule = academyScheduleService.findById(academyScheduleId)
        pay.itemName = kakaoPayApproval?.item_name
        pay.price = kakaoPayApproval?.amount?.total
        pay.payMethodType = kakaoPayApproval?.payment_method_type
        pay.tid = kakaoPayApproval?.tid
        pay.approvedAt = kakaoPayApproval?.approved_at.toString()
        return pay
    }


    fun insertPay(pay : Pay) : ResponseEntity<Pay>?{
        val insertPay : Pay? = payService.insertPay(pay) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(insertPay)
    }

    @GetMapping("/find/{memberId}")
    @ApiOperation(value = "멤버로 검색", notes = "멤버로 검색합니다")
    fun findByMember(@PathVariable memberId : Long) : ResponseEntity<List<Pay>>?{
        val member : Member? = memberService.findById(memberId)
        val pay : List<Pay>? = payService.findByMember(member) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(pay)
    }
}