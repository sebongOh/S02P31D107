package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonBackReference
import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name="member_academy")
class MemberAcademy     (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var memberAcademyId: Long? = null,

        @ManyToOne
        @JoinColumn(name="member_id")
        var member: Member? = null,

        @ManyToOne
        @JoinColumn(name="academy_id")
        var academy: Academy? = null
) : BaseEntity()
