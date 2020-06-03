package com.ssafy.learnacademy.controller

import com.ssafy.learnacademy.service.AcademyService
import com.ssafy.learnacademy.service.MemberService
import com.ssafy.learnacademy.service.ReviewService
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Review
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/review")
class ReviewController (var reviewService: ReviewService, var academyService: AcademyService, var memberService: MemberService){

    @GetMapping("/")
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

    @PostMapping
}