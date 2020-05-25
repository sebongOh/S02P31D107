package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyScheduleService
import com.ssafy.learnacademy.vo.AcademySchedule
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/academySchedule")
class AcademyScheduleController (var academyScheduleService: AcademyScheduleService){

    @GetMapping
    @ApiOperation(value="학원 스케쥴 전체검색", notes = "학원 스케쥴을 전부검색합니다")
    fun getAllAcademySchedule() : List<AcademySchedule>? = academyScheduleService.findAll()

    @GetMapping("/{academyScheduleId}")
    @ApiOperation(value = "학원 스케쥴 검색", notes = "학원 스케쥴을 검색합니다")
    fun getAcademySchedule(@PathVariable("academyScheduleId") academyScheduleId : Long) : ResponseEntity<AcademySchedule>?{
        return academyScheduleService.findById(academyScheduleId)?.map { academySchedule ->
            ResponseEntity.ok(academySchedule)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @PostMapping
    @ApiOperation(value="학원 스케쥴 등록", notes = "학원스케쥴을 등록합니다")
    fun insertAcademySchedule(@RequestBody academySchedule: AcademySchedule) : AcademySchedule?{
        return academyScheduleService.insertAcademySchedule(academySchedule)
    }

    @PutMapping
    @ApiOperation(value="학원 스케쥴 수정",notes = "학원 스케쥴을 수정합니다")
    fun updateAcademySchedule(@RequestBody academySchedule: AcademySchedule) : ResponseEntity<AcademySchedule>?{
        return academyScheduleService.findById(academySchedule.academyScheduleId)?.map {
            academyScheduleService.updateAcademySchedule(academySchedule)
            ResponseEntity<AcademySchedule>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @DeleteMapping("/{academyScheduleId}")
    @ApiOperation(value = "학원 스케쥴 삭제", notes = "학원 스케쥴을 삭제합니다")
    fun deleteAcademySchedule(@PathVariable("academyScheduleId") academyScheduleId: Long) : ResponseEntity<Void>? {
        return academyScheduleService.findById(academyScheduleId)?.map{ academySchedule ->
            academyScheduleService.deleteAcademySchedule(academySchedule)
            ResponseEntity<Void>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }
}