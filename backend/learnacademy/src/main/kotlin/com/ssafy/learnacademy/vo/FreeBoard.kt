package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name="free_board")
class FreeBoard (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var freeBoardId: Int? = null,

    @Column(nullable = false)
    var title : String? = null,

    @Column(nullable = false)
    var contents : String? = null,

    @ManyToOne
    @JoinColumn(name="member_id")
    var member: Member? = null,

    @ManyToOne
    @JoinColumn(name="academy_id")
    var academy: Academy? = null
) : BaseEntity()