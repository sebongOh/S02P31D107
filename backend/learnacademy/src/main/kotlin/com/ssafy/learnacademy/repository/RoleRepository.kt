package com.ssafy.learnacademy.repository


import com.ssafy.learnacademy.vo.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Int>