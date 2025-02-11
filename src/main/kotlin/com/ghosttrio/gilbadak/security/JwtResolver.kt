package com.ghosttrio.gilbadak.security

import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class JwtResolver {

    fun extractingToken(accessToken: String?): String? {
        if (accessToken == null ||
            !StringUtils.hasText(accessToken) ||
            !accessToken.startsWith("Bearer ")) {
            return null
        }
        return accessToken.substring(7)
    }

}