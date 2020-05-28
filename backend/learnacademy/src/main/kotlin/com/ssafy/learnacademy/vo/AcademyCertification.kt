package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import org.springframework.web.multipart.MultipartFile
import javax.persistence.*

@Entity
@Table(name="academy_certification")
class AcademyCertification(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var academyCertificationId: Long? = null,

    @Column(nullable = false)
    var imageUrl: String? = null,

    @ManyToOne
    @JoinColumn(name="member_id")
    var member: Member? = null,

    @ManyToOne
    @JoinColumn(name="academy_id")
    var academy: Academy? = null
) :BaseEntity()