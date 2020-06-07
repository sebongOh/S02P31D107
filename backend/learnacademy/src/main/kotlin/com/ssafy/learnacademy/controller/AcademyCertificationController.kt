package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyCertificationService
import com.ssafy.learnacademy.service.AcademyManagementAuthorityService
import com.ssafy.learnacademy.vo.AcademyCertification
import com.ssafy.learnacademy.vo.AcademyManagementAuthority
import com.ssafy.learnacademy.vo.Board
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.io.PrintWriter
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/academy-certification")
//@CrossOrigin(origins = ["*"], maxAge = 3600)
class AcademyCertificationController(
    val acacemyCertificationService: AcademyCertificationService,
    val academyManagementAuthorityService: AcademyManagementAuthorityService
) {

    @GetMapping("/")
    @ApiOperation(value = "학원 인증 요청전체찾기", notes = "학원 인증 요청을 전부검색합니다")
    fun getAllBoard() : ResponseEntity<List<AcademyCertification>>? {
        val academyCertificationList : List<AcademyCertification>? = acacemyCertificationService.findAll() ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(academyCertificationList)
    }

    @GetMapping("/{academyCertificationId}")
    @ApiOperation(value = "학원 인증 요청 조회", notes = "학원 인증 요청을 검색합니다")
    fun getBoard(@PathVariable("academyCertificationId") academyCertificationId : Long) : ResponseEntity<AcademyCertification>?{
        var academyCertification : AcademyCertification? =  null
        try {
            academyCertification = acacemyCertificationService.findById(academyCertificationId) ?: return ResponseEntity.noContent().build()
        } catch (e: Exception) {
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.ok().body(academyCertification)
    }

    @PostMapping
    @ApiOperation(value = "학원 인증 요청 등록", notes = "학원 인증 요청 을 등록합니다")
    fun insertBoard(@RequestBody academyCertification: AcademyCertification) : ResponseEntity<AcademyCertification>?{
        val insertAcademyCertification : AcademyCertification? = acacemyCertificationService.insert(academyCertification) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(insertAcademyCertification)
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "학원 인증 요청 삭제",notes = "학원 인증 요청을 삭제합니다")
    fun deleteBoard(@PathVariable("academyCertificationId") academyCertificationId : Long): ResponseEntity<Unit>? {
        val academyCertification : AcademyCertification? = acacemyCertificationService.findById(academyCertificationId) ?: return ResponseEntity.notFound().build()
        acacemyCertificationService.delete(academyCertificationId!!)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/permit/{academyCertificationId}")
    fun permitCertification(@PathVariable("academyCertificationId") academyCertificationId: Long, response: HttpServletResponse) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        val out: PrintWriter = response.getWriter();
        val academyCertification: AcademyCertification = acacemyCertificationService.findById(academyCertificationId)
        academyCertification.state = 1
        acacemyCertificationService.update(academyCertification)

        // AcademyManagementAuthority 추가해주기
        val academyManagementAuthority: AcademyManagementAuthority = AcademyManagementAuthority()
        academyManagementAuthority.member = academyCertification.member;
        academyManagementAuthority.academy = academyCertification.academy;
        val insertAcademyManagerAuthority = academyManagementAuthorityService.insert(academyManagementAuthority)
        out.println("권한이 승인되었습니다");
        out.flush();
    }
}