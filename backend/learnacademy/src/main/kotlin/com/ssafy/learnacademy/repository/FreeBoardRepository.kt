package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.FreeBoard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FreeBoardRepository : JpaRepository<FreeBoard, Long>