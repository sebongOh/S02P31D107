package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import org.springframework.web.multipart.MultipartFile
import javax.persistence.*

@Entity
@Table(name="academy")
class Academy (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var academyId: Long? = null,

        @Column(nullable = false)
        var name: String? = null,

        @Column(nullable = false)
        var address: String? = null,

        @Column(nullable = false)
        var phone: String? = null,

        @Column(nullable = false)
        var category: String? = null,

        @Column(nullable = false)
        var imageUrl: String? = null,

        @Transient
        var imageFile: MultipartFile? = null

) : BaseEntity()
