package com.ssafy.learnacademy.vo

import javax.persistence.*

@Entity
@Table(name="academy")
class Academy (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var academyId: Int? = null,
        var name: String? = null,
        var address: String? = null,
        var phone: String? = null,
        var category: String? = null,
        var imageUrl: String? = null,

        @OneToOne(mappedBy = "academy")
        var member: Member? = null
)