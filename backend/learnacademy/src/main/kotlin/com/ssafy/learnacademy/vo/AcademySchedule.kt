package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "academy_schedule")
class AcademySchedule (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var academyScheduleId: Long,

        @Column(nullable = false)
        var name : String? = null,

        @Column(nullable = false)
        var price : Int? = null,

        @ManyToOne
        @JoinColumn(name="academy_id")
        var academy: Academy? = null,

        @OneToMany(mappedBy = "academySchedule",cascade = [CascadeType.ALL])
        var scheduleDetail: MutableList<ScheduleDetail>? = null,

        @OneToMany(mappedBy = "academySchedule",cascade = [CascadeType.ALL])
        var memberSchedule: MutableList<MemberSchedule>? = null
) : BaseEntity()