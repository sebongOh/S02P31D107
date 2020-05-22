package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.NoticeBoard
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NoticeBoardRepository : CrudRepository<NoticeBoard,Long>