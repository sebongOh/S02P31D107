package com.ssafy.learnacademy.repository


import com.ssafy.learnacademy.vo.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long>