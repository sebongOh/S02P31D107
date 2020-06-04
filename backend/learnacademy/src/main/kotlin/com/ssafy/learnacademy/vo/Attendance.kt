package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonBackReference
import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "attendance")
class Attendance (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var attendanceId: Long? = null,

        @Column(nullable = false)
        var date : String? = null,

        @Column(nullable = false)
        var type : Byte? = null,

        @ManyToOne
        @JoinColumn(name="member_schedule_id")
        @JsonBackReference
        var memberSchedule: MemberSchedule? = null

) : BaseEntity()