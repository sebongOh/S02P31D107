package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberScheduleService
import com.ssafy.learnacademy.vo.MemberSchedule
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/memberSchedule")
class MemberScheduleController(var memberScheduleService: MemberScheduleService) {

    @GetMapping("/")
    @ApiOperation(value = "사용자 스케쥴 전체찾기", notes = "사용자 스케쥴을 전체 찾습니다")
    fun getAllMemberSchedule() = memberScheduleService.findAll()

    @GetMapping("/{memberScheduleId}")
    @ApiOperation(value = "사용자 스케쥴 검색", notes = "사용자 스케쥴 검색")
    fun getMemberSchedule(@PathVariable("memberScheduleId") memberScheduleId : Long) : Optional<MemberSchedule>?{
        return memberScheduleService.findById(memberScheduleId)
    }

    @PostMapping
    @ApiOperation(value="사용자 스케쥴 등록", notes = "사용자 스케쥴을 등록합니다")
    fun insertMemberSchedule(@RequestBody memberSchedule: MemberSchedule) : MemberSchedule?{
        return memberScheduleService.insertMemberSchedule(memberSchedule)
    }

    @PutMapping
    @ApiOperation(value = "사용자 스케쥴 수정", notes = "사용자 스케쥴을 수정합니다")
    fun updateMemberSchedule(@RequestBody memberSchedule: MemberSchedule) : MemberSchedule?{
        return memberScheduleService.updateMemberSchedule(memberSchedule)
    }

    @DeleteMapping("/{memberScheduleId}")
    @ApiOperation(value= "사용자 스케쥴 삭제", notes = "사용자 스케쥴을 삭제합니다")
    fun deleteMemberSchedule(@PathVariable("memberScheduleId") memberScheduleId: Long){
        return memberScheduleService.deleteMemberSchedule(memberScheduleId)
    }
}