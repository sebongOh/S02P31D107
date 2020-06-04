package com.ssafy.learnacademy.vo


import org.joda.time.DateTime
import java.time.LocalDateTime
import java.util.*


data class KakaoPayReady (

        var tid : String?=null,
        var next_redirect_app_url : String?=null,
        var next_redirect_mobile_url : String?=null,
        var next_redirect_pc_url : String?=null,
        var android_app_scheme : String?=null,
        var ios_app_scheme : String?=null,
        var created_at : Date?=null
)