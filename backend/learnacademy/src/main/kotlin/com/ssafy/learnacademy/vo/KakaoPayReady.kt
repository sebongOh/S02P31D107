package com.ssafy.learnacademy.vo


import java.time.LocalDateTime


data class KakaoPayReady (

        var tid : String?=null,
        var next_redirect_pc_url : String?=null,
        var created_at : LocalDateTime
)