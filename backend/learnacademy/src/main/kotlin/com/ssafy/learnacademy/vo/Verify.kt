package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name="verify")
class Verify (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var verifyId: Long? = null,

    @Column(nullable = false)
    var email: String? = null,

    @Column(nullable = false)
    var code: String? = null
) : BaseEntity()