package com.ssafy.learnacademy.vo

class Amount (
        var total : Int, // 전체 결제 금액
        var tax_free : Int, // 비과세 금액
        var vat : Int, // 부가세 금액
        var discount : Int, // 사용한 포인트
        var point : Int // 할인 금액
)