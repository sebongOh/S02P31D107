package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.AcademyRepository
import com.ssafy.learnacademy.vo.Academy
import org.springframework.stereotype.Service
import java.util.*

@Service
class AcademyService(var academyRepository: AcademyRepository) {

    fun findAll() : List<Academy>? {
        return academyRepository.findAll()
    }

    fun getAcademy(academyId: Long): Academy? {
        return academyRepository.findById(academyId).get()
    }

    fun insertAcademy(academy: Academy): Academy? {
        return academyRepository.save(academy)
    }

    fun updateAcademy(academy: Academy): Academy? {
        return academyRepository.save(academy)
    }

    fun deleteAcademy(academy: Academy) {
        return academyRepository.delete(academy)
    }
}