package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.service.S3UploadService
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Board
import com.ssafy.learnacademy.vo.Member
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/academy")
//@CrossOrigin(origins = ["*"], maxAge = 3600)
class AcademyController(
        val academyService: AcademyService,
        val s3UploadService: S3UploadService
) {

    @GetMapping
    @ApiOperation(value="학원 전체 찾기", notes = "학원을 전부 검색합니다")
    fun getAllAcademy() : ResponseEntity<List<Academy>>?{
        val academy : List<Academy>? = academyService.findAll() ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(academy)
    }

    @GetMapping("/{academyId}")
    @ApiOperation(value="학원 찾기", notes = "학원을 검색합니다")
    fun getAcademy(@PathVariable("academyId") academyId: Long): ResponseEntity<Academy>? {
        var  academy : Academy? = null
        try {
            academy = academyService.getAcademy(academyId) ?: return ResponseEntity.noContent().build()
        } catch (e: Exception) {
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.ok().body(academy)
    }

    // 학원 계정 아이디로 해당 학원 조회 (Academy)

    @PostMapping
    @ApiOperation(value="학원 등록", notes = "학원을 등록합니다")
    fun insertAcademy(@RequestBody academy: Academy): ResponseEntity<Academy>? {
        academy.contents = "학원 소개글이 없습니다."
        val insertAcademy : Academy? = academyService.insertAcademy(academy) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(insertAcademy)
    }

    @PutMapping
    @ApiOperation(value="학원 수정", notes = "학원을 수정합니다")
    fun updateAcademy(@RequestBody academy: Academy): ResponseEntity<Academy>? {
        academyService.getAcademy(academy.academyId!!) ?: return ResponseEntity.noContent().build()
        if (academy.imageFile != null) {
            academy.imageUrl = s3UploadService.uploadFile(academy.imageFile, "academy/")
        }
        academy.contents = academy.contents?.replace("\n", "<br>")
        academyService.updateAcademy(academy)
        return ResponseEntity.ok().body(academy)
    }

    @DeleteMapping("/{academyId}")
    @ApiOperation(value="학원 삭제", notes = "학원을 삭제합니다")
    fun deleteAcademy(@PathVariable("academyId") academyId: Long):ResponseEntity<Unit>? {
        val academy : Academy? = academyService.getAcademy(academyId) ?: return ResponseEntity.notFound().build()
        academyService.deleteAcademy(academy!!)
        return ResponseEntity.ok().build()
    }
}