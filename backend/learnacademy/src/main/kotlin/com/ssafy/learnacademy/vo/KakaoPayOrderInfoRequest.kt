package com.ssafy.learnacademy.vo

data class KakaoPayOrderInfoRequest (
        var cid : String? = null, // 가맹점 코드 10자
        var tid : String? = null // 결제 고유번호 20자
)
