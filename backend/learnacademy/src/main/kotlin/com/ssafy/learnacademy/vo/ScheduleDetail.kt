package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "schedule_detail")
class ScheduleDetail (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var scheduleDetailId: Long,

        @Column(nullable = false)
        var startTime : String? = null,

        @Column(nullable = false)
        var endTime : String? = null,

        @Column(nullable = false)
        var dayOfTheWeek : String? = null,

        @ManyToOne
        @JoinColumn(name="academy_schedule_id")
        var academySchedule: AcademySchedule? = null
) : BaseEntity()