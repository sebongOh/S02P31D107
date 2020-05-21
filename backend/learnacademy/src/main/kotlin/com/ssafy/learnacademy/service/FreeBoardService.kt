package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.FreeBoardRepository
import com.ssafy.learnacademy.vo.FreeBoard
import org.springframework.stereotype.Service
import java.util.*

@Service
class FreeBoardService(var freeBoardRepository: FreeBoardRepository){

    fun findAll() : Iterable<FreeBoard>?{
        return freeBoardRepository.findAll()
    }

    fun findById(freeBoardId : Long) : Optional<FreeBoard>? {
        return freeBoardRepository.findById(freeBoardId)
    }

    fun insertFreeBoard(freeBoard : FreeBoard) : FreeBoard?{
        return freeBoardRepository.save(freeBoard)
    }

    fun updateFreeBoard(freeBoard : FreeBoard) : FreeBoard?{
        return freeBoardRepository.save(freeBoard)
    }

    fun deleteFreeBoard(freeBoardId : Long){
        return freeBoardRepository.deleteById(freeBoardId)
    }
}