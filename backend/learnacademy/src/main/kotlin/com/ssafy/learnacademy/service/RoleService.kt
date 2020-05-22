package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.RoleRepository
import com.ssafy.learnacademy.vo.Role
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleService (var roleRepository: RoleRepository){
    fun findAll() : Iterable<Role>?{
        return roleRepository.findAll()
    }

    fun findById(roleId : Long) : Optional<Role>?{
        return roleRepository.findById(roleId)
    }

    fun insertRole(role: Role) : Role?{
        return roleRepository.save(role)
    }

    fun updateRole(role: Role) : Role?{
        return roleRepository.save(role)
    }

    fun deleteRole(roleId: Long){
        return roleRepository.deleteById(roleId)
    }
}