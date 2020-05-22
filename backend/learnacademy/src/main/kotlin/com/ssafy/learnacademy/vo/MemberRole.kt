package com.ssafy.learnacademy.vo

import javax.persistence.*

@Entity
@Table(name = "member_role")
class MemberRole (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long? = null,

        @ManyToOne
        @JoinColumn(name="member_id")
        var member: Member? = null,

        @ManyToOne
        @JoinColumn(name="role_id")
        var role: Role? = null
)