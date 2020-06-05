package com.ssafy.learnacademy.vo

data class SelectedCardInfo (
        var card_bin : String? = null, //카드 BIN
        var install_month : Int? = null, //할부 개월 수
        var card_corp_name : String? = null, //카드사 정보
        var interest_free_install : String? = null //무이자할부 여부(Y/N)
)