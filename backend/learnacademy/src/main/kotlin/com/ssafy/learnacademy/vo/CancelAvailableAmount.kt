package com.ssafy.learnacademy.vo

data class CancelAvailableAmount (
        var total : Int?=null, //전체 취소 가능 금액
        var tax_free : Int?=null, //취소 가능한 비과세 금액
        var vat : Int?=null, //취소 가능한 부가세 금액
        var point : Int?=null, //취소 가능한 포인트 금액
        var discount : Int?=null //취소 가능한 할인 금액
)