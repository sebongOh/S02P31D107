package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "child")
class Child (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var childId : Long?=null,

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    var member: Member?=null,

    @Column(nullable = false)
    var childNum : Long?=null
)