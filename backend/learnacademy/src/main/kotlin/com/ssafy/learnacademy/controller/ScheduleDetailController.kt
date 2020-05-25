package com.ssafy.learnacademy.controller


import com.ssafy.learnacademy.service.ScheduleDetailService
import com.ssafy.learnacademy.vo.ScheduleDetail
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/scheduleDetail")
class ScheduleDetailController (var scheduleDetailService: ScheduleDetailService){

    @GetMapping
    @ApiOperation(value="학원 스케쥴 상세정보 전체검색", notes = "학원 스케쥴 상세정보를 전부검색합니다")
    fun getAllScheduleDetail() : List<ScheduleDetail>? = scheduleDetailService.findAll()

    @GetMapping("/{scheduleDetailId}")
    @ApiOperation(value = "학원 스케쥴 상세정보 검색", notes = "학원 스케쥴 상세정보를 검색합니다")
    fun getScheduleDetail(@PathVariable("scheduleDetailId") scheduleDetailId : Long) : ResponseEntity<ScheduleDetail>?{
        return scheduleDetailService.findById(scheduleDetailId)?.map { scheduleDetail ->
            ResponseEntity.ok(scheduleDetail)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @PostMapping
    @ApiOperation(value="학원 스케쥴 상세정보 등록", notes = "학원스케쥴 상세정보를 등록합니다")
    fun insertScheduleDetail(@RequestBody scheduleDetail: ScheduleDetail) : ScheduleDetail?{
        return scheduleDetailService.insertScheduleDetail(scheduleDetail)
    }

    @PutMapping
    @ApiOperation(value="학원 스케쥴 상세정보 수정",notes = "학원 스케쥴 상세정보를 수정합니다")
    fun updateScheduleDetail(@RequestBody scheduleDetail: ScheduleDetail) : ResponseEntity<ScheduleDetail>?{
        return scheduleDetailService.findById(scheduleDetail.scheduleDetailId)?.map {
            scheduleDetailService.updateScheduleDetail(scheduleDetail)
            ResponseEntity<ScheduleDetail>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @DeleteMapping("/{scheduleDetailId}")
    @ApiOperation(value = "학원 스케쥴 상세정보 삭제", notes = "학원 스케쥴 상세정보를 삭제합니다")
    fun deleteScheduleDetail(@PathVariable("scheduleDetailId") scheduleDetailId: Long) : ResponseEntity<Void>? {
        return scheduleDetailService.findById(scheduleDetailId)?.map{ scheduleDetail ->
            scheduleDetailService.deleteScheduleDetail(scheduleDetail)
            ResponseEntity<Void>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }
}