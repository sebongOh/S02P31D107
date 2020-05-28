package com.ssafy.learnacademy.vo

import javax.persistence.*

@Entity
@Table(name = "child")
class Child (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var childId : Long,

    @ManyToOne
    var member: Member,

    @Column(nullable = false)
    var childNum : Long
)