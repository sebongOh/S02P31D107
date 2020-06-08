package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyScheduleService
import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.AcademySchedule
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/academy-schedule")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class AcademyScheduleController (var academyScheduleService: AcademyScheduleService, var academyService: AcademyService){

    @GetMapping
    @ApiOperation(value="학원 스케쥴 전체검색", notes = "학원 스케쥴을 전부검색합니다")
    fun getAllAcademySchedule() : ResponseEntity<List<AcademySchedule>>?{
        val academySchedule : List<AcademySchedule>? = academyScheduleService.findAll() ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(academySchedule)
    }

    @GetMapping("/{academyScheduleId}")
    @ApiOperation(value = "학원 스케쥴 검색", notes = "학원 스케쥴을 검색합니다")
    fun getAcademySchedule(@PathVariable("academyScheduleId") academyScheduleId : Long) : ResponseEntity<AcademySchedule>?{
       val academySchedule : AcademySchedule? = academyScheduleService.findById(academyScheduleId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(academySchedule)
    }
    @GetMapping("/academy/{academyId}")
    @ApiOperation(value = "학원 스케쥴 검색", notes = "학원 아이디로 학원 스케쥴을 검색합니다")
    fun getAcademyScheduleByAcademyId(@PathVariable("academyId") academyId : Long) : ResponseEntity<MutableList<AcademySchedule>>?{

        val academyScheduleList : MutableList<AcademySchedule> = academyScheduleService.findByAcademyId(academyId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(academyScheduleList)
    }

    @PostMapping
    @ApiOperation(value="학원 스케쥴 등록", notes = "학원스케쥴을 등록합니다")
    fun insertAcademySchedule(@RequestBody academySchedule: AcademySchedule) : ResponseEntity<AcademySchedule>?{
        academyService.getAcademy(academySchedule.academy?.academyId!!) ?: return ResponseEntity.noContent().build()
        val insertAcademySchedule : AcademySchedule? = academyScheduleService.insertAcademySchedule(academySchedule) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(insertAcademySchedule)
    }

    @PutMapping
    @ApiOperation(value="학원 스케쥴 수정",notes = "학원 스케쥴을 수정합니다")
    fun updateAcademySchedule(@RequestBody academySchedule: AcademySchedule) : ResponseEntity<AcademySchedule>?{
        academyScheduleService.findById(academySchedule.academyScheduleId!!) ?: return ResponseEntity.noContent().build()
        academyScheduleService.updateAcademySchedule(academySchedule)
        return ResponseEntity.ok().body(academySchedule)
    }

    @DeleteMapping("/{academyScheduleId}")
    @ApiOperation(value = "학원 스케쥴 삭제", notes = "학원 스케쥴을 삭제합니다")
    fun deleteAcademySchedule(@PathVariable("academyScheduleId") academyScheduleId: Long) : ResponseEntity<Unit>? {
        val academySchedule : AcademySchedule? = academyScheduleService.findById(academyScheduleId) ?: return ResponseEntity.noContent().build()
        academyScheduleService.deleteAcademySchedule(academySchedule!!)
        return ResponseEntity.ok().build()
    }
}