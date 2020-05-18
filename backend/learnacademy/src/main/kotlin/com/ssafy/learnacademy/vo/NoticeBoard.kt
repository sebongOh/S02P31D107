package com.ssafy.learnacademy.vo

import com.ssafy.learnacademy.common.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "notice_board")
class NoticeBoard (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var noticeBoardId: Int? = null,

        @Column(nullable = false)
        var title : String? = null,

        @Column(nullable = false)
        var contents : String? = null,

        @ManyToOne
        @JoinColumn(name="member_id")
        var member: Member,

        @ManyToOne
        @JoinColumn(name="academy_id")
        var academy: Academy
) : BaseEntity()