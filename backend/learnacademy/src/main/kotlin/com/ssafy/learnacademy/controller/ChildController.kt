package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.ChildService
import com.ssafy.learnacademy.vo.Child
import com.ssafy.learnacademy.vo.Member
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/child")
class ChildController (val childService: ChildService){

    @GetMapping("/")
    @ApiOperation(value="자식 모두 찾기", notes="자식을 모두 검색합니다")
    fun getAllChild() : ResponseEntity<List<Child>>{
        val child : List<Child> = childService.findAll() ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(child)
    }

    @GetMapping("/{childId}")
    @ApiOperation(value="자식 찾기", notes="자식을 검색합니다")
    fun getChild(@PathVariable("childId") childId : Long) : ResponseEntity<Child> {
        val child: Child? = childService.findById(childId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(child)
    }

    @PostMapping("/myChild")
    @ApiOperation(value="나의 자식 찾기", notes = "나의 자식을 검색합니다")
    fun getMyChild(@RequestBody member : Member) : ResponseEntity<List<Child>>{
        val child : List<Child>? = childService.findMyChild(member) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(child)
    }

    @PostMapping
    @ApiOperation(value="자식정보 등록", notes = "자식정보를 등록합니다")
    fun insertChild(@RequestBody child : Child) : ResponseEntity<Child>?{
        val child : Child? = childService.insertChild(child) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(child)
    }

    @PutMapping
    @ApiOperation(value="자식정보 수정", notes = "자식정보를 수정합니다")
    fun updateChild(@RequestBody child : Child) : ResponseEntity<Child>?{
        val child : Child? = childService.update(child) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(child)
    }

    @DeleteMapping("/{childId}")
    @ApiOperation(value="자식정보 삭제", notes="자식정보를 삭제합니다")
    fun deleteChild(@PathVariable("childId") childId: Long) : ResponseEntity<Unit>?{
        val child : Child? = childService.findById(childId)
        if(child == null){
            return ResponseEntity.notFound().build()
        }else {
            childService.deleteChild(child)
            return ResponseEntity.ok().build()
        }
    }
}