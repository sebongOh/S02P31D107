package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.ChildRepository
import com.ssafy.learnacademy.vo.Child
import com.ssafy.learnacademy.vo.Member
import org.springframework.stereotype.Service

@Service
class ChildService (var childRepository: ChildRepository){

    fun findAll() : List<Child>? {
        return childRepository.findAll()
    }

    fun findById(childId : Long) : Child?{
        return childRepository.findById(childId).get()
    }

    fun findMyChild(member : Member) : List<Child>?{
        return childRepository.findByMember(member)
    }

    fun insertChild(child: Child) : Child?{
        return childRepository.save(child)
    }

    fun updateChild(child: Child) : Child?{
        return childRepository.save(child)
    }

    fun deleteChild(child: Child){
        return childRepository.delete(child)
    }
}