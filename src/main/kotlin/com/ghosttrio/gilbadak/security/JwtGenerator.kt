package com.ghosttrio.gilbadak.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashMap

@Component
class JwtGenerator {

    fun createToken() {
        val secret = "123456abcdefg"
        val userId = 1L // 추후 수정
        val issuedAt = Date()
        val secretKeyBytes = Decoders.BASE64.decode(secret)
        val key: Key = Keys.hmacShaKeyFor(secretKeyBytes)

        val accessTokenTime = 30 * 60 * 1000L // 30분
        val claims: MutableMap<String, Any?> = HashMap()

        val nowTime = Timestamp.valueOf(LocalDateTime.now()).time
        val expiration = Date(nowTime + accessTokenTime)

        claims["nickname"] = "nickname"
        claims["email"] = "email"

        Jwts.builder()
            .signWith(key) // 서명 키
            .setExpiration(expiration) // 만료일
            .setIssuedAt(issuedAt) // 토큰 생성일
            .setSubject(userId.toString()) // 보통 사용자 id
            .setClaims(claims) // 사용자 정의 데이터
            .compact()
    }
}