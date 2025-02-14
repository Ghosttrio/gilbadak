package com.ghosttrio.gilbadak.security

import com.ghosttrio.gilbadak.user.domain.UserRole
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Component
class JwtGenerator(
    private val jwtProperties: JwtProperties
) {

    fun createToken(userId: Long?, email: String, role: UserRole) : JwtToken{
        val key = createKey()
        val issuedAt = Date()
        val expiration = createExpiration()
        val claims = createClaims(email, role)

        val accessToken = Jwts.builder()
            .signWith(key) // 서명 키
            .setExpiration(expiration) // 만료일
            .setIssuedAt(issuedAt) // 토큰 생성일
            .setSubject(userId.toString()) // 보통 사용자 id
            .setClaims(claims) // 사용자 정의 데이터
            .compact()

        // todo refresh token 생성 구현
        return JwtToken(accessToken, accessToken, expiration)
    }

    private fun createKey(): Key {
        val secret = jwtProperties.secret
        val secretKeyBytes = Decoders.BASE64.decode(secret)
        return Keys.hmacShaKeyFor(secretKeyBytes)
    }

    private fun createExpiration(): Date {
        val accessTokenTime = jwtProperties.accessTokenExpirationTime
        val nowTime = Timestamp.valueOf(LocalDateTime.now()).time
        return Date(nowTime + accessTokenTime)
    }

    private fun createClaims(email: String, role: UserRole): MutableMap<String, Any?> =
        mutableMapOf("email" to email, "role" to role)

    fun createAuthentication() {

    }

}