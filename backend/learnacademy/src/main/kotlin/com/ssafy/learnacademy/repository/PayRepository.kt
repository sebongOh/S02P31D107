package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Pay
import org.springframework.data.repository.CrudRepository

interface PayRepository : CrudRepository<Pay,Int>