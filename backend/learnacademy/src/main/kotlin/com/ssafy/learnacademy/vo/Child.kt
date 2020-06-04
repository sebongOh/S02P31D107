package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "child")
class Child (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var childId : Long,

    @ManyToOne
    @Column(name = "member_id")
    @JsonBackReference
    var member: Member,

    @Column(nullable = false)
    var childNum : Long
)