package com.ssafy.learnacademy.vo

data class CanceledAmount (
        var total : Int?=null, //	전체 취소 금액
        var tax_free : Int?=null, //취소된 비과세 금액
        var vat : Int?=null, //취소된 부가세 금액
        var point : Int?=null, //취소된 포인트 금액
        var discount : Int?=null //취소된 할인 금액
)