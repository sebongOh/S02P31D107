package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonBackReference
import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "academy_management_authority")
class AcademyManagementAuthority (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var academyManagementAuthorityId: Long,

    @ManyToOne
    @JoinColumn(name="member_id")
    @JsonBackReference
    var member: Member? = null,

    @ManyToOne
    @JoinColumn(name="academy_id")
    @JsonBackReference
    var academy: Academy? = null
) :BaseEntity()