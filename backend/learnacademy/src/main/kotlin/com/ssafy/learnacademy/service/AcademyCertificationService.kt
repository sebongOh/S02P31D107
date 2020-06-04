package com.ssafy.learnacademy.service

import com.ssafy.learnacademy.repository.AcademyCertificationRepository
import com.ssafy.learnacademy.vo.AcademyCertification
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.io.File

@Service
class AcademyCertificationService(
        val academyCertificationRepository: AcademyCertificationRepository,
        val mailSender: JavaMailSender
) {

    fun sendCertificationInfoToAdmin(academyCertification: AcademyCertification) {
        val message = SimpleMailMessage()
        message.setTo("learnacademy02@gmail.com")
        message.setSubject("[배울학] 학원 권한 요청이 접수되었습니다.") // 제목
        val contents = "학원 권한 요청이 접수되었습니다. 인증 정보를 확인 후 아래 URL을 클릭하여 권한을 승인하십시오.\n\n" + "" +
                "URL : http://localhost:8080/academy-certification/permit/" + academyCertification.academyCertificationId + "\n\n" +
                "학원 이름 : " + academyCertification.academy?.name + "\n" +
                "요청 사용자 : " + academyCertification.member?.name + "\n" +
                "사업자 등록 인증서 : \n" + academyCertification.imageUrl
        message.setText(contents)
        mailSender.send((message))
        return
    }

    fun findAll(): MutableList<AcademyCertification> {
        return academyCertificationRepository.findAll()
    }

    fun findById(academyCertificationId: Long): AcademyCertification {
        return academyCertificationRepository.findById(academyCertificationId).get()
    }

    fun insert(academyCertification: AcademyCertification): AcademyCertification {
        return academyCertificationRepository.save(academyCertification)
    }

    fun update(academyCertification: AcademyCertification): AcademyCertification {
        return academyCertificationRepository.save(academyCertification)
    }

    fun delete(academyCertificationId: Long) {
        return academyCertificationRepository.deleteById(academyCertificationId)
    }
}