package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonBackReference
import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "pay")
class Pay (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var payId : Long? =null,

        @ManyToOne
        @JoinColumn(name="member_id")
        @JsonBackReference
        var member: Member? = null,

        @ManyToOne
        @JoinColumn(name="academy_schedule_id")
        @JsonBackReference
        var academySchedule: AcademySchedule? = null,

        var tid : String? =null,

        var itemName : String? =null,

        var price : Int? =null,

        var payMethodType : String? =null,

        var approvedAt : String? =null
)
