package com.ssafy.learnacademy.Security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
class SecurityConfiguration(val jwtTokenProvider: JwtTokenProvider) : WebSecurityConfigurerAdapter() {

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .httpBasic().disable() //rest api 이므로 기본설정 X
                .csrf().disable() //csrf 해제
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() //다음 리퀘스트에 대한 사용권한체크
                .antMatchers("/**", "/*/signin", "/*/signup", "/*/sendEmail", "/*/checkCode", "/*/findPassword").permitAll() //앤트매처 로그인시
                .antMatchers(HttpMethod.GET, "api/**").permitAll() //get요청 열기
                .antMatchers(HttpMethod.OPTIONS, "api/**").permitAll() //get요청 열기
                .anyRequest().authenticated().and().cors() //그외 유저롤만 접근
                .and()
                .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
    }

    //swagger ignoring
    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/member",
                "/member/**",
                "/verify/**",
                "/verify"
                )
    }
}
