package com.ssafy.learnacademy.controller


import com.ssafy.learnacademy.service.AcademyScheduleService
import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.service.ScheduleDetailService
import com.ssafy.learnacademy.vo.AcademySchedule
import com.ssafy.learnacademy.vo.ScheduleDetail
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/schedule-detail")
class ScheduleDetailController (var scheduleDetailService: ScheduleDetailService,
                                var academyScheduleService : AcademyScheduleService,
                                var academyService : AcademyService){

    @GetMapping
    @ApiOperation(value="학원 스케쥴 상세정보 전체검색", notes = "학원 스케쥴 상세정보를 전부검색합니다")
    fun getAllScheduleDetail() : ResponseEntity<List<ScheduleDetail>>?{
        val scheduleDetail : List<ScheduleDetail>? = scheduleDetailService.findAll() ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(scheduleDetail)
    }

    @GetMapping("/{scheduleDetailId}")
    @ApiOperation(value = "학원 스케쥴 상세정보 검색", notes = "학원 스케쥴 상세정보를 검색합니다")
    fun findByScheduleDetailId(@PathVariable("scheduleDetailId") scheduleDetailId : Long) : ResponseEntity<ScheduleDetail>?{
        val scheduleDetail : ScheduleDetail? = scheduleDetailService.findById(scheduleDetailId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(scheduleDetail)
    }

    @GetMapping("/{academyScheduleId}/asid")
    @ApiOperation(value="상세 스케쥴 학원스케쥴로 찾기",notes = "상세 스케쥴을 학원스케쥴로 검색합니다")
    fun findByAcademyScheduleId(@PathVariable("academyScheduleId") academyScheduleId : Long) : ResponseEntity<List<ScheduleDetail>>{
        val academySchedule : AcademySchedule? = academyScheduleService.findById(academyScheduleId) ?: return ResponseEntity.noContent().build()
        val scheduleDetail : List<ScheduleDetail>? = scheduleDetailService.findByAcademySchedule(academySchedule!!) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(scheduleDetail)
    }


    @PostMapping
    @ApiOperation(value="학원 스케쥴 상세정보 등록", notes = "학원스케쥴 상세정보를 등록합니다")
    fun insertScheduleDetail(@RequestBody scheduleDetail: ScheduleDetail) : ResponseEntity<ScheduleDetail>?{
       academyService.getAcademy(scheduleDetail.academySchedule?.academy?.academyId!!) ?: return ResponseEntity.noContent().build()
       academyScheduleService.findById(scheduleDetail.academySchedule?.academyScheduleId!!) ?: return ResponseEntity.noContent().build()
        val insertScheduleDetail : ScheduleDetail? = scheduleDetailService.insertScheduleDetail(scheduleDetail) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(insertScheduleDetail)
    }

    @PutMapping
    @ApiOperation(value="학원 스케쥴 상세정보 수정",notes = "학원 스케쥴 상세정보를 수정합니다")
    fun updateScheduleDetail(@RequestBody scheduleDetail: ScheduleDetail) : ResponseEntity<ScheduleDetail>?{
        academyService.getAcademy(scheduleDetail.academySchedule?.academy?.academyId!!) ?: return ResponseEntity.noContent().build()
        academyScheduleService.findById(scheduleDetail.academySchedule?.academyScheduleId!!) ?: return ResponseEntity.noContent().build()
        scheduleDetailService.findById(scheduleDetail.scheduleDetailId!!) ?: return ResponseEntity.noContent().build()
        scheduleDetailService.updateScheduleDetail(scheduleDetail)
        return ResponseEntity.ok().body(scheduleDetail)
    }

    @DeleteMapping("/{scheduleDetailId}")
    @ApiOperation(value = "학원 상세 스케쥴 삭제", notes = "학원 스케쥴 상세정보를 삭제합니다")
    fun deleteById(@PathVariable("scheduleDetailId") scheduleDetailId: Long) : ResponseEntity<Unit>? {
        val scheduleDetail : ScheduleDetail? = scheduleDetailService.findById(scheduleDetailId) ?: return ResponseEntity.noContent().build()
        scheduleDetailService.deleteScheduleDetail(scheduleDetail!!)
        return ResponseEntity.ok().build()
    }
}