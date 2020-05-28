package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long>