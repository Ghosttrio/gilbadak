package com.ghosttrio.gilbadak.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val secret: String = "",
    val accessTokenExpirationTime: String = "",
    val refreshTokenExpirationTime: String = ""
)
