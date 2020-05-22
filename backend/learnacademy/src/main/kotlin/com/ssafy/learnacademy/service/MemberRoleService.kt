package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.MemberRoleRepository
import com.ssafy.learnacademy.vo.MemberRole
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberRoleService(var memberRoleRepository: MemberRoleRepository) {

    fun findAll() : Iterable<MemberRole>?{
        return memberRoleRepository.findAll()
    }

    fun findById(memberRoleId : Long) : Optional<MemberRole>?{
        return memberRoleRepository.findById(memberRoleId)
    }

    fun insertMemberRole(memberRole: MemberRole) : MemberRole?{
        return memberRoleRepository.save(memberRole)
    }

    fun updateMemberRole(memberRole: MemberRole) : MemberRole?{
        return memberRoleRepository.save(memberRole)
    }

    fun deleteMemberRole(memberRoleId : Long){
        return memberRoleRepository.deleteById(memberRoleId)
    }
}