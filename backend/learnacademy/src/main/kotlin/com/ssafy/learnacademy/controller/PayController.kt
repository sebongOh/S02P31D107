package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyScheduleService
import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.service.PayService
import com.ssafy.learnacademy.vo.*
import io.swagger.annotations.Api
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
@CrossOrigin(origins = ["*"], maxAge = 3600)
class PayController(var payService: PayService, var memberService: MemberService, var academyScheduleService: AcademyScheduleService, var academyService: AcademyService) {

    final var HOST : String = "https://kapi.kakao.com/"
    var kakaoPayApproval: KakaoPayApproval? =null
    var kakaoPayReady: KakaoPayReady? = null
    var kakaoPayOrder : KakaoPayOrder? = null
    var kakaoPayCancel : KakaoPayCancel? = null
    final val webUrl : String = "http://192.168.35.48:8080/"

    @PostMapping("/ready")
    @ApiOperation(value = "카카오페이 결제준비", notes = "카카오페이 결제를준비합니다")
    fun kakaoPayReady(@RequestBody payReadyRequest : PayReadyRequest, request : HttpServletRequest) : ResponseEntity<String>{
        var headers = HttpHeaders()
        var restTemplate = RestTemplate()

        headers.add("Authorization","KakaoAK "+"b6c1c0d9dcaeedd5745508c7b9d7e133")
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE+";charset=utf-8")


        val schedule : AcademySchedule? = academyScheduleService.findById(payReadyRequest.scheduleId!!)
        val member : Member? = memberService.findById(payReadyRequest.memberId!!)

        println("###############  요청정보  #####################")
        println("가격 : "+schedule?.price)
        println("물품명 : "+schedule?.name)
        println("학원명 : "+schedule?.academy?.name)
        println("구매자명 : "+member?.name)
        println("###############################################")


        var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("cid","TC0ONETIME")
        params.add("partner_order_id", schedule?.academy?.name) // 학원명
        params.add("partner_user_id", member?.name) // 구매자
        params.add("item_name", schedule?.name) // 상품명
        params.add("quantity", "1")
        params.add("total_amount", schedule?.price.toString()) // 가격
        params.add("tax_free_amount","0")
        params.add("approval_url", webUrl+"#/paymenting?memberId="+payReadyRequest.memberId+"&scheduleId="+payReadyRequest.scheduleId)
        params.add("cancel_url",webUrl)
        params.add("fail_url",webUrl)

        var body : HttpEntity<MultiValueMap<String,String>> = HttpEntity<MultiValueMap<String,String>>(params,headers)
        println("################   결제정보  ###################")
        println(body.headers.toString())
        println(body.body.toString())
        println("###############################################")
        try {
            kakaoPayReady = restTemplate.postForObject(URI("https://kapi.kakao.com/v1/payment/ready"), body, KakaoPayReady::class.java)
            return ResponseEntity<String>(kakaoPayReady!!.next_redirect_pc_url, HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/paySuccess")
    @ApiOperation(value="카카오페이 결제완료", notes = "카카오페이 결제완료")
    fun kakaoPayInfo(@RequestBody payRequest: PayRequest) : ResponseEntity<Pay>{
        var restTemplate = RestTemplate()
        var headers = HttpHeaders()

        println("############## 결제 요청정보  ###################")
        println("스케쥴번호 : "+payRequest.scheduleId)
        println("멤버번호 : "+payRequest.memberId)
        println("토큰정보 : "+payRequest.pg_token)
        println("###############################################")

        headers.add("Authorization","KakaoAK "+"b6c1c0d9dcaeedd5745508c7b9d7e133")
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8")

        var academyName : String? = academyScheduleService.findById(payRequest.scheduleId!!)?.academy?.name
        var memberName : String? = memberService.findById(payRequest.memberId!!)?.name
        var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReady?.tid);
        params.add("partner_order_id", academyName);
        params.add("partner_user_id", memberName);
        params.add("pg_token", payRequest.pg_token);

        var body : HttpEntity<MultiValueMap<String,String>> = HttpEntity<MultiValueMap<String,String>>(params,headers)

        println("################   결제정보  ###################")
        println(body.headers.toString())
        println(body.body.toString())
        println("###############################################")

        try {
            kakaoPayApproval = restTemplate.postForObject(URI(HOST + "v1/payment/approve"), body,
                    KakaoPayApproval::class.java)
            val pay: Pay = createPay(kakaoPayApproval, payRequest.scheduleId!!, payRequest.memberId!!)
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

    @PostMapping("/cancel")
    @ApiOperation(value="카카오페이 결제취소", notes = "카카오페이 결제취소하기")
    fun kakaoPayCancel(@RequestBody payCancelRequest : PayCancelRequest, request: HttpRequest) : ResponseEntity<KakaoPayCancel>{

        var headers = HttpHeaders()
        var restTemplate = RestTemplate()

        headers.add("Authorization","KakaoAK "+"b6c1c0d9dcaeedd5745508c7b9d7e133")
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE+";charset=utf-8")

        println("###############  취소 요청정보  #################")
        println("cid : "+payCancelRequest.cid)
        println("tid : "+payCancelRequest.tid)
        println("취소금액 : "+ payCancelRequest.cancel_amount)
        println("취소 비과세금액 : "+ payCancelRequest.cancel_tax_free_amount)
        println("###############################################")


        var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("cid",payCancelRequest.cid)
        params.add("tid",payCancelRequest.tid)
        params.add("cancel_amount",payCancelRequest.cancel_amount.toString())
        params.add("cancel_tax_free_amount",payCancelRequest.cancel_tax_free_amount.toString())
        var body : HttpEntity<MultiValueMap<String,String>> = HttpEntity<MultiValueMap<String,String>>(params,headers)
        println("################   취소정보  ###################")
        println(body.headers.toString())
        println(body.body.toString())
        println("###############################################")
        try {
            kakaoPayCancel = restTemplate.postForObject(URI("https://kapi.kakao.com/v1/payment/cancel"), body, KakaoPayCancel::class.java)
            return ResponseEntity<KakaoPayCancel>(kakaoPayCancel, HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)

    }

    @PostMapping("/order")
    @ApiOperation(value="카카오페이 주문조회", notes = "카카오페이 주문내역조회")
    fun kakaoPayOrderInfo(@RequestBody kakaoPayOrderInfoRequest: KakaoPayOrderInfoRequest, request : HttpRequest) : ResponseEntity<KakaoPayOrder>{
        var headers = HttpHeaders()
        var restTemplate = RestTemplate()

        headers.add("Authorization","KakaoAK "+"b6c1c0d9dcaeedd5745508c7b9d7e133")
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE)
        headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE+";charset=utf-8")

        println("##############  조회 요청정보  #################")
        println("cid : "+kakaoPayOrderInfoRequest.cid)
        println("tid : "+kakaoPayOrderInfoRequest.tid)
        println("##############################################")

        var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("cid",kakaoPayOrderInfoRequest.cid)
        params.add("tid",kakaoPayOrderInfoRequest.tid)

        var body : HttpEntity<MultiValueMap<String,String>> = HttpEntity<MultiValueMap<String,String>>(params,headers)
        println("################   조회정보  ###################")
        println(body.headers.toString())
        println(body.body.toString())
        println("###############################################")

        try {
            kakaoPayOrder = restTemplate.postForObject(URI("https://kapi.kakao.com/v1/payment/order"), body, KakaoPayOrder::class.java)
            return ResponseEntity<KakaoPayOrder>(kakaoPayOrder, HttpStatus.OK)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


    @GetMapping("/{memberId}/member")
    @ApiOperation(value = "멤버결제내역 검색", notes = "멤버결제내역을 검색합니다")
    fun findByMember(@PathVariable memberId : Long) : ResponseEntity<List<Pay>>?{
        val member : Member? = memberService.findById(memberId)
        val pay : List<Pay>? = payService.findByMember(member) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(pay)
    }

    @GetMapping("/{scheduleId}/schedule")
    @ApiOperation(value="스케쥴의 결제내역 검색", notes = "스케쥴의 결제내역들을 검색합니다")
    fun findBySchedule(@PathVariable scheduleId : Long) : ResponseEntity<List<Pay>>?{
        val schedule : AcademySchedule? = academyScheduleService.findById(scheduleId) ?: return ResponseEntity.noContent().build()
        val pay : List<Pay>? = payService.findBySchedule(schedule!!) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(pay)
    }

    @GetMapping("/{academyId}/academy")
    @ApiOperation(value = "학원 결제내역 검색", notes = "학원의 결제내역을 검색합니다")
    fun findByAcademy(@PathVariable academyId : Long) : ResponseEntity<List<Pay>>?{
        val pay : List<Pay> = payService.findByAcademyId(academyId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(pay)
    }
}