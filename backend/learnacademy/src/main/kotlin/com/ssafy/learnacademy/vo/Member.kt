package com.ssafy.learnacademy.vo

import javax.persistence.*

@Entity
@Table(name="member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memberId: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var address: String? = null,
    var phone: String? = null,
    var age: Int? = null,
    var gender: Byte? = null,
    var childId: Int? = null,

    @OneToOne
    @JoinColumn(name = "academy_id")
    var academy: Academy? = null
)
