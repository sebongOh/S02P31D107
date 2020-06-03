package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.ReviewRepository
import com.ssafy.learnacademy.vo.Academy
import com.ssafy.learnacademy.vo.Member
import com.ssafy.learnacademy.vo.Review
import org.springframework.stereotype.Service

@Service
class ReviewService (var reviewRepository: ReviewRepository){

    fun findAll() : List<Review>?{
        return reviewRepository.findAll()
    }

    fun findByMember(member : Member) : List<Review>?{
        return reviewRepository.findByMember(member)
    }

    fun findById(reviewId : Long) : Review?{
        return reviewRepository.findById(reviewId).get()
    }

    fun findByAcademy(academy : Academy) : List<Review>?{
        return reviewRepository.findByAcademy(academy)
    }

    fun updateReview(review : Review) : Review?{
        return reviewRepository.save(review)
    }

    fun insertReview(review : Review) : Review?{
        return reviewRepository.save(review)
    }

    fun deleteReview(review : Review){
        return reviewRepository.delete(review)
    }
}