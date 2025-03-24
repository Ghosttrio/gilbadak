package com.ghosttrio.gilbadak.security

import java.util.*

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
    val expirationTime: Date
)
