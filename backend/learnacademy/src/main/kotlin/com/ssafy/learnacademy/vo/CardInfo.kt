package com.ssafy.learnacademy.vo

class CardInfo (
        var interest_free_install : String, // 무이자 할부
        var bin : String, // 카드 BIN
        var card_type : String = "체크", // 카드 타입
        var card_mid : String, // 카드 가맹점 번호
        var approved_id : String, // 카드 승인번호
        var install_month : String, // 할부 개월 수
        var purchase_corp : String, // 매입 카드사 한글명
        var purchase_corp_code: String, // 매입 카드사 코드
        var issuer_corp : String, // 카드 발급사 한글명
        var issuer_corp_code : String, // 카드 발급사 코드
        var kakaopay_purchase_corp : String, // 카카오페이 매입사 명
        var kakaopay_purchase_corp_code: String, // 카카오페이 매입사 코드
        var kakaopay_issuer_corp : String, // 카카오페이 발급사 명
        var kakaopay_issuer_corp_code : String // 카카오페이 발급사 코드
)