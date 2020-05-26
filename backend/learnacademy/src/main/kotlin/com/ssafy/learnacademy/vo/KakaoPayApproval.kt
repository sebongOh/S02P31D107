package com.ssafy.learnacademy.vo

import java.time.LocalDateTime

class KakaoPayApproval (
    var aid : String,
    var tid : String,
    var cid : String = "TC0ONETIME",
    var sid : String,
    var partner_order_id : String,
    var partner_user_id : String,
    var payment_method_type : String = "CARD",
    var created_at : LocalDateTime,
    var approved_at : LocalDateTime,
    var quantity : Int,
    var tax_free_amount : Int,
    var vat_amount : Int,
    var item_name : String,
    var item_code : String,
    var payload : String,
    var member : Member,
    var academySchedule: AcademySchedule
)