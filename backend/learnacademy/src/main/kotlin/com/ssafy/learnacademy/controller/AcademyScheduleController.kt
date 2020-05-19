package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyScheduleService
import com.ssafy.learnacademy.vo.AcademySchedule
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/academySchedule")
class AcademyScheduleController (var academyScheduleService: AcademyScheduleService){

    @GetMapping
    @ApiOperation(value="학원 스케쥴 전체검색", notes = "학원 스케쥴을 전부검색합니다")
    fun getAllAcademySchedule() = academyScheduleService.findAll()

    @GetMapping("/{academyScheduleId}")
    @ApiOperation(value = "학원 스케쥴 검색", notes = "학원 스케쥴을 검색합니다")
    fun getAcademySchedule(@PathVariable("academyScheduleId") academyScheduleId : Int) : Optional<AcademySchedule>?{
        return academyScheduleService.findById(academyScheduleId)
    }

}