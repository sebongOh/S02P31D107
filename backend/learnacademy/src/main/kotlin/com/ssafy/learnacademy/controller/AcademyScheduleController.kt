package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyScheduleService
import com.ssafy.learnacademy.vo.AcademySchedule
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/academySchedule")
class AcademyScheduleController (var academyScheduleService: AcademyScheduleService){

    @GetMapping
    @ApiOperation(value="학원 스케쥴 전체검색", notes = "학원 스케쥴을 전부검색합니다")
    fun getAllAcademySchedule() = academyScheduleService.findAll()

    @GetMapping("/{academyScheduleId}")
    @ApiOperation(value = "학원 스케쥴 검색", notes = "학원 스케쥴을 검색합니다")
    fun getAcademySchedule(@PathVariable("academyScheduleId") academyScheduleId : Long) : Optional<AcademySchedule>?{
        return academyScheduleService.findById(academyScheduleId)
    }

    @PostMapping
    @ApiOperation(value="학원 스케쥴 등록", notes = "학원스케쥴을 등록합니다")
    fun insertAcademySchedule(@RequestBody academySchedule: AcademySchedule) : AcademySchedule?{
        return academyScheduleService.insertAcademySchedule(academySchedule)
    }

    @PutMapping
    @ApiOperation(value="학원 스케쥴 수정",notes = "학원 스케쥴을 수정합니다")
    fun updateAcademySchedule(@RequestBody academySchedule: AcademySchedule) : AcademySchedule?{
        return academyScheduleService.updateAcademySchedule(academySchedule)
    }

    @DeleteMapping("/{academyScheduleId")
    @ApiOperation(value = "학원 스케쥴 삭제", notes = "학원 스케쥴을 삭제합니다")
    fun deleteAcademySchedule(@PathVariable("academyScheduleId") academyScheduleId: Long){
        return academyScheduleService.deleteAcademySchedule(academyScheduleId)
    }
}