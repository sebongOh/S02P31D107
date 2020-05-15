package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.AcademyRepository
import com.ssafy.learnacademy.vo.Academy
import org.springframework.stereotype.Service
import java.util.*

@Service
class AcademyService(var academyRepository: AcademyRepository) {

    fun getAcademy(academyId: Int): Optional<Academy>? {
        return academyRepository.findById(academyId)
    }

    fun insertAcademy(academy: Academy): Academy? {
        return academyRepository.save(academy)
    }

    fun updateAcademy(academy: Academy): Academy? {
        return academyRepository.save(academy)
    }

    fun deleteAcademy(academyId: Int) {
        return academyRepository.deleteById(academyId)
    }
}