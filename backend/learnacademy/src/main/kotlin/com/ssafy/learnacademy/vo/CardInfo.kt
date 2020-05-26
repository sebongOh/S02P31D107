package com.ssafy.learnacademy.vo

class CardInfo (
        var interest_free_install : String,
        var bin : String,
        var card_type : String = "체크",
        var card_mid : String,
        var approved_id : String,
        var install_month : String,
        var purchase_corp : String,
        var purchase_corp_code: String,
        var issuer_corp : String,
        var issuer_corp_code : String,
        var kakaopay_purchase_corp : String,
        var kakaopay_purchase_corp_code: String,
        var kakaopay_issuer_corp : String,
        var kakaopay_issuer_corp_code : String
)