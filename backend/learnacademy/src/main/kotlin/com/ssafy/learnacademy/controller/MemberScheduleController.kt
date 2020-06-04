package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberScheduleService
import com.ssafy.learnacademy.vo.MemberSchedule
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/member-schedule")
@CrossOrigin(origins = ["*"], maxAge = 3600)
class MemberScheduleController(var memberScheduleService: MemberScheduleService) {

    @GetMapping
    @ApiOperation(value = "사용자 스케쥴 전체찾기", notes = "사용자 스케쥴을 전체 찾습니다")
    fun getAllMemberSchedule() : List<MemberSchedule>? = memberScheduleService.findAll()

    @GetMapping("/{memberScheduleId}")
    @ApiOperation(value = "사용자 스케쥴 검색", notes = "사용자 스케쥴 검색")
    fun getMemberSchedule(@PathVariable("memberScheduleId") memberScheduleId : Long) : ResponseEntity<MemberSchedule>?{
        return memberScheduleService.findById(memberScheduleId)?.map { memberSchedule->
            ResponseEntity.ok(memberSchedule)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @PostMapping
    @ApiOperation(value="사용자 스케쥴 등록", notes = "사용자 스케쥴을 등록합니다")
    fun insertMemberSchedule(@RequestBody memberSchedule: MemberSchedule) : MemberSchedule?{
        return memberScheduleService.insertMemberSchedule(memberSchedule)
    }

    @PutMapping
    @ApiOperation(value = "사용자 스케쥴 수정", notes = "사용자 스케쥴을 수정합니다")
    fun updateMemberSchedule(@RequestBody memberSchedule: MemberSchedule) : ResponseEntity<MemberSchedule>?{
        return memberScheduleService.findById(memberSchedule.memberScheduleId)?.map {
            memberScheduleService.updateMemberSchedule(memberSchedule)
            ResponseEntity<MemberSchedule>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }

    @DeleteMapping("/{memberScheduleId}")
    @ApiOperation(value= "사용자 스케쥴 삭제", notes = "사용자 스케쥴을 삭제합니다")
    fun deleteMemberSchedule(@PathVariable("memberScheduleId") memberScheduleId: Long) : ResponseEntity<Void>?{
        return memberScheduleService.findById(memberScheduleId)?.map { memberSchedule->
            memberScheduleService.deleteMemberSchedule(memberSchedule)
            ResponseEntity<Void>(HttpStatus.OK)
        }?.orElse(ResponseEntity.noContent().build())
    }
}