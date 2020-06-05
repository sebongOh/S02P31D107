package com.ssafy.learnacademy.vo

data class PaymentActionDetails (

        var aid : String? = null, //Request 고유 번호
        var approved_at : String? = null, //거래시간
        var amount : Int? = null, //결제/취소 총액
        var point_amount : Int? = null, //결제/취소 포인트 금액
        var discount_amount : Int? = null, //할인 금액
        var payment_action_type : String? = null, //결제 타입
        // PAYMENT(결제), CANCEL(결제취소), ISSUED_SID(SID 발급) 중 하나
        var payload : String? = null //Request로 전달한 값
)