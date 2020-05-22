package com.ssafy.learnacademy.repository


import com.ssafy.learnacademy.vo.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : CrudRepository<Role, Long>