package com.ghosttrio.gilbadak.user.controller

import com.ghosttrio.gilbadak.security.JwtToken
import com.ghosttrio.gilbadak.user.controller.model.request.SigninRequest
import com.ghosttrio.gilbadak.user.service.AuthService
import com.ghosttrio.gilbadak.util.GilbadakResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class SigninController(
    private val authService: AuthService
) {
    @PostMapping("/signin")
    fun signin(@RequestBody request: SigninRequest): GilbadakResponse<JwtToken> {
        val result = authService.signin(request)
        return GilbadakResponse.ok(result)
    }
}