package com.ssafy.learnacademy.vo

data class PayCancelRequest (
        var cid : String? = null, // 가맹점 코드, 10자
        var tid : String? = null, // 결제 고유번호
        var cancel_amount : Int? = null, //취소 금액
        var cancel_tax_free_amount : Int? = null // 취소 비과세 금액
)