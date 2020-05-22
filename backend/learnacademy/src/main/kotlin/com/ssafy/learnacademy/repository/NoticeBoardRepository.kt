package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.NoticeBoard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NoticeBoardRepository : JpaRepository<NoticeBoard, Long>