package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name="review")
class Review (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var reviewId: Long? = null,

        @Column(nullable = false)
        var title : String? = null,

        @Column(nullable = false)
        var contents : String? = null,

        @Column(nullable = false)
        var score : Double? = null,

        @ManyToOne
        @JoinColumn(name="academy_id")
        var academy : Academy? = null
) : BaseEntity()