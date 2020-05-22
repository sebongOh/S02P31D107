package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Pay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PayRepository : JpaRepository<Pay, Long>