package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "academy_schedule")
class AcademySchedule (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var academyScheduleId: Int? = null,

        @Column(nullable = false)
        var name : String? = null,

        @Column(nullable = false)
        var startTime : String? = null,

        @Column(nullable = false)
        var endTime : String? = null,

        @Column(nullable = false)
        var dayOfTheWeek : String? = null,

        @Column(nullable = false)
        var price : Int? = null,

        @ManyToOne
        @JoinColumn(name="academy_id")
        var academy: Academy
) : BaseEntity()