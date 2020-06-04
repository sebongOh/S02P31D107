package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.ssafy.learnacademy.common.BaseEntity
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "schedule_detail")
class ScheduleDetail (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var scheduleDetailId: Long? = null,

        @Column(nullable = false)
        var startTime : String? = null,

        @Column(nullable = false)
        var endTime : String? = null,

        @Column(nullable = false)
        var dayOfTheWeek : String? = null,

        @ManyToOne
        @JoinColumn(name = "academy_schedule_id")
        @JsonBackReference
        var academySchedule: AcademySchedule? = null
) : BaseEntity()