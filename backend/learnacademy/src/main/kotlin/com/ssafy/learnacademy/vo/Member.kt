package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.ssafy.learnacademy.common.BaseEntity
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.persistence.*

@Entity
@Table(name="member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

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

    @Column(nullable = false)
    var profileUrl: String? = null,

    @Transient
    var profileFile: MultipartFile? = null,

    var childId: Int? = null
) : BaseEntity()
