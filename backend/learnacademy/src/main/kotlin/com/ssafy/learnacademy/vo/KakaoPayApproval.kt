package com.ssafy.learnacademy.vo

import java.time.LocalDateTime

class KakaoPayApproval (
    var aid : String? =null, // 요청 고유 번호
    var tid : String? =null, // 결제 고유 번호
    var cid : String? = "TC0ONETIME", // 가맹점 코드
    var sid : String? =null, // 정기 결제용 ID, 정기결제 CID로 단건결제 요청시 발급
    var partner_order_id : String? =null, // 가맹점 주문번호
    var partner_user_id : String? =null, // 가맹점 회원 id
    var payment_method_type : String? = "CARD", // 결제수단 CARD or MONEY
    var amount : Amount? =null, // 결제금액 정보
    var card_info : CardInfo? =null, // 결제 상세 정보, 결제수단이 카드일 경우만 포함
    var created_at : LocalDateTime? =null, // 결제 준비 요청 시각
    var approved_at : LocalDateTime? =null, // 결제 승인 시각
    var quantity : Int? =null, // 상품 수량
    var item_name : String? =null, // 상품 이름
    var item_code : String? =null, // 상품 코드
    var payload : String? =null // 결제승인 요청에 대해 저장한 값
)