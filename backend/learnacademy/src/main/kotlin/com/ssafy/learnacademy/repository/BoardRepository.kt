package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b INNER JOIN Academy a ON b.academy.academyId = a.academyId WHERE a.academyId = ?1 AND b.type = 0")
    fun findNoticeAllByAcademyId(academyId: Long): List<Board>?

    @Query("SELECT b FROM Board b INNER JOIN Academy a ON b.academy.academyId = a.academyId WHERE a.academyId = ?1 AND b.type = 1")
    fun findReferenceAllByAcademyId(academyId: Long): List<Board>?
}