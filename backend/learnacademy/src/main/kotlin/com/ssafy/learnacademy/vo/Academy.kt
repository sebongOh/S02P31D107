package com.ssafy.learnacademy.vo

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.ssafy.learnacademy.common.BaseEntity
=======
>>>>>>> f3c05686b0e82c4b9b9b175af51ee78ea2776782
import javax.persistence.*

@Entity
@Table(name="academy")
class Academy (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var academyId: Int? = null,

        @Column(nullable = false)
        var name: String? = null,

        @Column(nullable = false)
        var address: String? = null,

        @Column(nullable = false)
        var phone: String? = null,

        @Column(nullable = false)
        var category: String? = null,

        @Column(nullable = false)
        var imageUrl: String? = null
) : BaseEntity()
