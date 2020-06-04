package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.service.MemberAcademyService
import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.service.ReviewService
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.MemberAcademy
import com.ssafy.learnacademy.vo.Review
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/review")
class ReviewController (var reviewService: ReviewService,
                        var academyService: AcademyService,
                        var memberService: MemberService,
                        var memberAcademyService: MemberAcademyService){

    @GetMapping
    @ApiOperation(value = "전체 리뷰 검색", notes = "전체 리뷰를 검색합니다")
    fun getAllReview() : ResponseEntity<List<Review>>? {
        val review : List<Review>? = reviewService.findAll() ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(review)
    }
    @GetMapping("/{reviewId}")
    @ApiOperation(value = "리뷰 검색", notes = "리뷰를 검색합니다")
    fun getReview(@PathVariable("reviewId") reviewId : Long) : ResponseEntity<Review>?{
        val review : Review? = reviewService.findById(reviewId) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(review)
    }

    @GetMapping("/{academyId}/academy")
    @ApiOperation(value = "학원 리뷰 검색", notes = "학원 리뷰를 검색합니다")
    fun getAcademyReview(@PathVariable("academyId") academyId : Long) : ResponseEntity<List<Review>>?{
        val academy : Academy = academyService.getAcademy(academyId) ?: return ResponseEntity.noContent().build()
        val review : List<Review> = reviewService.findByAcademy(academy) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(review)
    }

    @GetMapping("/{memberId}/member")
    @ApiOperation(value = "멤버 리뷰 검색", notes = "멤버 리뷰를 검색합니다")
    fun getMemberReview(@PathVariable("memberId") memberId : Long) : ResponseEntity<List<Review>>?{
        val member : Member = memberService.findById(memberId) ?: return ResponseEntity.noContent().build()
        val review : List<Review> = reviewService.findByMember(member) ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok().body(review)
    }

//    @PostMapping
//    @ApiOperation(value = "리뷰 등록", notes = "리뷰를 등록합니다")
//    fun insertReview(@RequestBody review : Review) : ResponseEntity<Review>?{
//        // 회원이 학원을 가지고 있어야함...
//        // 회원이 학원을 등록한 상태여야함...
//        // 멤버아카데미에서 회원으로 검색을한다음 list안에 있는지 체크필요
//        return ResponseEntity.ok().body()
//    }
}