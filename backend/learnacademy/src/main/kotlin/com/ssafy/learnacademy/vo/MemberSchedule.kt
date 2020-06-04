package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonBackReference
import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "member_schedule")
class MemberSchedule (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var memberScheduleId: Long,

        @Column(nullable = false)
        var startDate : String?=null,

        @Column(nullable = false)
        var endDate : String? = null,

        @ManyToOne
        @JoinColumn(name="member_id")
        var member: Member? = null,

        @ManyToOne
        @JoinColumn(name="academy_schedule_id")
        var academySchedule: AcademySchedule? = null
) : BaseEntity()