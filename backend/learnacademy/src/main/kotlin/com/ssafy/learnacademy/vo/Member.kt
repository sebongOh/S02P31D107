package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.ssafy.learnacademy.common.BaseEntity
import org.springframework.web.multipart.MultipartFile
import javax.persistence.*

@Entity
@Table(name="member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memberId: Long? = null,

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

    @OneToMany(mappedBy = "member")
    var roles: MutableSet<Role> = mutableSetOf(),

    @Transient
    @JsonIgnore
    var profileFile: MultipartFile? = null
) : BaseEntity()
