package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.FreeBoard
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FreeBoardRepository : CrudRepository<FreeBoard,Long>