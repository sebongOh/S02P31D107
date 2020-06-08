package com.ssafy.learnacademy.Security

import com.ssafy.learnacademy.service.CustomUserDetailService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
        val costomUserDetailsService: CustomUserDetailService
){
    @Value("Spring.jwt.secret")
    private var secretKey: String? = null
    private val tokenValidMilisecond = 1000L * 60 * 60 * 3 // 3시간 후 만료

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey!!.toByteArray())
    }

    //토큰 생성
    fun createToken(userPk: String?, roles: MutableList<String>): String {
        val claims = Jwts.claims().setSubject(userPk)
        claims["roles"] = roles
        val now = Date()
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(Date(now.getTime() + tokenValidMilisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey) //HS256 알고리즘
                .compact()
    }

    //토큰 인증 정보 조회
    fun getAuthentication(token: String?): Authentication {
        val userDetails: UserDetails = costomUserDetailsService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    //토큰에서 회원 구별 정보 추출
    fun getUserPk(token: String?): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    //request의 Header에서 token 파싱
    fun resolveToken(req: HttpServletRequest): String {
        return req.getHeader("X-AUTH-TOKEN")
    }

    //jwt 토큰의 유효성 + 만료일자 validation
    fun valiateToken(jwtToken: String?): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}
