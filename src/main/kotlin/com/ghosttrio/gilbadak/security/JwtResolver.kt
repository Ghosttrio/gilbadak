package com.ghosttrio.gilbadak.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.security.Key

@Component
class JwtResolver(
    private val jwtProperties: JwtProperties
) {

    fun extractingToken(accessToken: String?): String? {
        if (accessToken == null ||
            !StringUtils.hasText(accessToken) ||
            !accessToken.startsWith("Bearer ")) {
            return null
        }
        return accessToken.substring(7)
    }

    fun extractingUserId(token: String?): Long {
        return Jwts
            .parserBuilder()
            .setSigningKey(createKey())
            .build()
            .parseClaimsJws(token)
            .body
            .subject.toLong()
    }

    private fun createKey(): Key {
        val secret = jwtProperties.secret
        val secretKeyBytes = Decoders.BASE64.decode(secret)
        return Keys.hmacShaKeyFor(secretKeyBytes)
    }



}