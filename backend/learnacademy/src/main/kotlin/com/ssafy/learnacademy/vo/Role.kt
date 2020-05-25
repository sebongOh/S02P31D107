package com.ssafy.learnacademy.vo

import javax.persistence.*

@Entity
@Table(name="role")
class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var roleId : Long? = null,

    @Column(nullable = false)
    var role: String? = null,

    @ManyToOne
    @JoinColumn(name="member_id")
    var member: Member? = null,

    @ManyToOne
    @JoinColumn(name="academy_id")
    var academy: Academy? = null
)