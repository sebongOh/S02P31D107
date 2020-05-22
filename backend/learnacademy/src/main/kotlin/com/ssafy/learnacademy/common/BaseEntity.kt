package com.ssafy.learnacademy.common


import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity (
    @CreatedDate
    private val createDate: LocalDateTime? = LocalDateTime.now(),

    @LastModifiedDate
    private val modifiedDate: LocalDateTime? = LocalDateTime.now()
)