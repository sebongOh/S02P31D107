package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/academy")
class AcademyController(var academyService: AcademyService) {
    @GetMapping("/{academyId}")
    fun getAcademy(@PathVariable("academyId") academyId: Int): Optional<Academy>? {
        return academyService.getAcademy(academyId)
    }

    // 학원 계정 아이디로 해당 학원 조회 (Academy)

    @PostMapping
    fun insertAcademy(@RequestBody academy: Academy): Academy? {
        return academyService.insertAcademy(academy)
    }

    @PutMapping
    fun updateAcademy(@RequestBody academy: Academy): Academy? {
        return academyService.updateAcademy(academy)
    }

    @DeleteMapping("/{academyId}")
    fun deleteAcademy(@PathVariable("academyId") academyId: Int) {
        return academyService.deleteAcademy(academyId)
    }
}