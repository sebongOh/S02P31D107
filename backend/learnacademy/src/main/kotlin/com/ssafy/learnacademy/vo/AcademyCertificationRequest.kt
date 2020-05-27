package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import org.springframework.web.multipart.MultipartFile
import javax.persistence.*

class AcademyCertificationRequest (
        var memberId: Long? = null,

        var name: String? = null,

        var email: String? = null,

        var password: String? = null,

        var address: String? = null,

        var phone: String? = null,

        var age: Int? = null,

        var gender: Byte? = null,

        var profileUrl: String? = null,

        var roles: MutableSet<Role> = mutableSetOf(),

        var profileFile: MultipartFile? = null,

        var childId: Int? = null,

        var imageUrl: String? = null,

        var imageFile: MultipartFile? = null,

        var academyId: Long? = null
) : BaseEntity()
