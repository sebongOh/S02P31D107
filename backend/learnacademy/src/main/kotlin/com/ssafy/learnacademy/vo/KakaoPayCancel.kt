package com.ssafy.learnacademy.vo

import java.util.*
import javax.xml.crypto.Data

data class KakaoPayCancel(
        var aid : String? =null, // 요청 고유 번호
        var tid : String? =null, // 결제 고유 번호
        var cid : String? = "TC0ONETIME", // 가맹점 코드
        var status : String? =null, // 결제상태
        var partner_order_id : String?= null, //가맹점 주문번호 최대 100자
        var partner_user_id : String? = null, // 가맹점 회원 id 최대 100자
        var payment_method_type : String? = "CARD", // 결제수단
        var amount : Amount? =null, //결제 금액 정보
        var canceled_amount : CanceledAmount? = null, //이번 요청으로 취소된 금액
        var cancel_available_amount : CancelAvailableAmount? = null, //남은 취소 가능 금액
        var item_name : String? = null, //상품 이름, 최대 100자
        var item_code : String? = null, //상품 코드, 최대 100자
        var quantity : Int? = null, //상품 수량
        var created_at : Date? = null, //결제 준비 요청 시각
        var approved_at : Data? = null,//결제 승인 시각
        var canceled_at : Date? = null, //결제 취소 시각
        var payload : String? = null //취소 요청 시 전달한 값
)