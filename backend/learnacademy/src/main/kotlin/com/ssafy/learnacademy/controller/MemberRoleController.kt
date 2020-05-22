package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.MemberRoleService
import com.ssafy.learnacademy.vo.MemberRole
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/memberRole")
class MemberRoleController (var memberRoleService: MemberRoleService){

    @GetMapping
    @ApiOperation(value = "멤버권한 전체검색" , notes = "멤버들의 권한을 전부 검색합니다")
    fun getAllMemberRole() = memberRoleService.findAll()

    @GetMapping("/{memberRoleId}")
    @ApiOperation(value="멤버권한 검색", notes = "멤버의 권한을 검색합니다")
    fun getMemberRole(@PathVariable("memberRoleId") memberRoleId : Long): Optional<MemberRole>?{
        return memberRoleService.findById(memberRoleId)
    }

    @PostMapping
    @ApiOperation(value="멤버권한 등록", notes = "멤버의 권한을 등록합니다")
    fun insertMemberRole(@RequestBody memberRole : MemberRole) : MemberRole?{
        return memberRoleService.insertMemberRole(memberRole)
    }

    @PutMapping
    @ApiOperation(value= "멤버권한 수정", notes = "멤버의 권한을 수정합니다")
    fun updateMemberRole(@RequestBody memberRole : MemberRole) : MemberRole?{
        return memberRoleService.updateMemberRole(memberRole)
    }

    @DeleteMapping("/{memberRoleId}")
    @ApiOperation(value="멤버권한 삭제", notes = "멤버권한을 삭제합니다")
    fun deleteMemberRole(@PathVariable("memberRoleId") memberRoleId: Long){
        return memberRoleService.deleteMemberRole(memberRoleId)
    }
}