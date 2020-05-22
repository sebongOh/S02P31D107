package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.MemberRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRoleRepository : JpaRepository<MemberRole, Long>