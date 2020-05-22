package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.RoleService
import com.ssafy.learnacademy.vo.Role
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/role")
class RoleController(var roleService : RoleService) {
    @GetMapping
    @ApiOperation(value = "멤버권한 전체검색" , notes = "멤버들의 권한을 전부 검색합니다")
    fun getAllRole() = roleService.findAll()

    @GetMapping("/{roleId}")
    @ApiOperation(value="멤버권한 검색", notes = "멤버의 권한을 검색합니다")
    fun getRole(@PathVariable("roleId") roleId : Long): Optional<Role>?{
        return roleService.findById(roleId)
    }

    @PostMapping
    @ApiOperation(value="멤버권한 등록", notes = "멤버의 권한을 등록합니다")
    fun insertRole(@RequestBody role : Role) : Role?{
        return roleService.insertRole(role)
    }

    @PutMapping
    @ApiOperation(value= "멤버권한 수정", notes = "멤버의 권한을 수정합니다")
    fun updateRole(@RequestBody role : Role) : Role?{
        return roleService.updateRole(role)
    }

    @DeleteMapping("/{roleId}")
    @ApiOperation(value="멤버권한 삭제", notes = "멤버권한을 삭제합니다")
    fun deleteRole(@PathVariable("roleId") roleId: Long){
        return roleService.deleteRole(roleId)
    }
}