package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.MemberRole
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRoleRepository : CrudRepository<MemberRole,Long>