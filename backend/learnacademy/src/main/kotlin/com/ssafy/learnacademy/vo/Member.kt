package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.ssafy.learnacademy.common.BaseEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name="member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memberId: Int? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false, unique = true)
    var email: String? = null,

    @Column(nullable = false)
    var password: String? = null,

    @Column(nullable = false)
    var address: String? = null,

    @Column(nullable = false)
    var phone: String? = null,

    @Column(nullable = false)
    var age: Int? = null,

    @Column(nullable = false)
    var gender: Byte? = null,

    var childId: Int? = null,

    @OneToOne
    @JoinColumn(name = "academy_id")
    var academy: Academy? = null
) : BaseEntity()
