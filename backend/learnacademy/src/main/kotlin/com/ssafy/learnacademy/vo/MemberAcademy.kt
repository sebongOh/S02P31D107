package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@Table(name="member_academy")
class MemberAcademy (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var memberAcademyId: Int? = null,

        @ManyToOne
        @JoinColumn(name="member_id")
        var member: Member,

        @ManyToOne
        @JoinColumn(name="academy_id")
        var academy: Academy
)