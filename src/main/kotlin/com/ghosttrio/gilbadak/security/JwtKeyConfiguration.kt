package com.ghosttrio.gilbadak.security

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.Key

@Configuration
class JwtKeyConfiguration(
    private val jwtProperties: JwtProperties
) {
    @Bean
    fun jwtKey(): Key {
        val keyBytes = Decoders.BASE64.decode(jwtProperties.secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}