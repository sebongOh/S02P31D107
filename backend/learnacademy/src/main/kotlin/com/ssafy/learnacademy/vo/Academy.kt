package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.ssafy.learnacademy.common.BaseEntity
import org.springframework.web.multipart.MultipartFile
import javax.persistence.*

@Entity
@Table(name="academy")
class Academy (
        @Id
        var academyId: Long? = null,

        @Column(nullable = false)
        var name: String? = null,

        @Column(nullable = false)
        var address: String? = null,

        @Column(nullable = false)
        var phone: String? = null,

        @Column(nullable = false)
        var contents: String? = null,

        @Column(nullable = false)
        var category: String? = null,

        @Column(nullable = false)
        var imageUrl: String? = null,

        @Transient
        var imageFile: MultipartFile? = null,

        @OneToMany(mappedBy = "academy", cascade = [CascadeType.ALL])
        @JsonIgnore
        var academySchedule: MutableList<AcademySchedule>? = null,

        @OneToMany(mappedBy = "academy", cascade = [CascadeType.ALL])
        @JsonIgnore
        var review : MutableList<Review>?=null,

        @OneToMany(mappedBy = "academy", cascade = [CascadeType.ALL])
        @JsonIgnore
        var memberAcademy: MutableList<MemberAcademy>?=null

) : BaseEntity()
