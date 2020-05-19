package com.ssafy.learnacademy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class LearnacademyApplication

fun main(args: Array<String>) {
    runApplication<LearnacademyApplication>(*args)
}
