package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name="board")
class Board (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var boardId: Long,

    @Column(nullable = false)
    var title : String? = null,

    @Column(nullable = false)
    var contents : String? = null,

    @Column(nullable = false)
    var type : Byte,

    @ManyToOne
    @JoinColumn(name="member_id")
    var member: Member? = null,

    @ManyToOne
    @JoinColumn(name="academy_id")
    var academy: Academy? = null
) : BaseEntity()