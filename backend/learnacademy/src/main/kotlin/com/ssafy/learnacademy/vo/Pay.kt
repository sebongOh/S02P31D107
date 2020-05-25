package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "pay")
class Pay (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var payId : Long,

        @Column(nullable = false)
        var cancel : Byte? = null,

        @ManyToOne
        @JoinColumn(name="member_id")
        var member: Member? = null,

        @ManyToOne
        @JoinColumn(name="academy_schedule_id")
        var academySchedule: AcademySchedule? = null
) : BaseEntity()
