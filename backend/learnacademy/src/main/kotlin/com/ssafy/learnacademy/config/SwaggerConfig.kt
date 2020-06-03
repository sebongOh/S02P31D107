package com.ssafy.learnacademy.config

import com.ssafy.learnacademy.Security.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Parameter
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig(val jwtTokenProvider: JwtTokenProvider) {
    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.learnacademy.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo())
//                .globalOperationParameters(authParam()) // 추가
    }

    private fun metaInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("배울학 API")
                .description("배울학 API입니다. 로그인 후 사용할 수 있는 모든 기능은 header에 X-AUTH-TOKEN : access_token 를 삽입하여 전송해 주세요.")
                .build()
    }

    private fun authParam(): List<Parameter> {
        val parameter = ParameterBuilder()
                .name("X-AUTH-TOKEN")
                .modelRef(ModelRef("string"))
                .parameterType("header")
                .defaultValue(jwtTokenProvider.createToken("swagger", mutableListOf("ROLE_USER", "ROLE_ACADEMY")))
                .required(true)
                .build()
        return listOf(parameter)
    }
}