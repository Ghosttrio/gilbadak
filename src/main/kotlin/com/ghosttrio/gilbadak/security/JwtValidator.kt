package com.ghosttrio.gilbadak.security

import io.jsonwebtoken.*
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.security.Key

@Component
class JwtValidator(
    private val jwtKey: Key
) {

    val log = KotlinLogging.logger {}

    fun validateAccessToken(accessToken: String?): Boolean {
        try {
            Jwts
                .parserBuilder()
                .setSigningKey(jwtKey)
                .build()
                .parseClaimsJws(accessToken)
            return true
        } catch (e: ExpiredJwtException) {
            log.error { "JWT 토큰이 만료되었습니다: ${e.message}" }
        } catch (e: MalformedJwtException) {
            log.error { "잘못된 형식의 JWT 토큰입니다: ${e.message}" }
        } catch (e: UnsupportedJwtException) {
            log.error { "지원되지 않는 JWT 토큰입니다: ${e.message}" }
        } catch (e: JwtException) {
            log.error { "JWT 처리 중 오류 발생: ${e.message}" }
        }
        return false
    }
}