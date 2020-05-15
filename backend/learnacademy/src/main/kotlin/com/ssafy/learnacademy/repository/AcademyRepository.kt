package com.ssafy.learnacademy.repository

import com.ssafy.learnacademy.vo.Academy
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AcademyRepository : CrudRepository<Academy, Int>
