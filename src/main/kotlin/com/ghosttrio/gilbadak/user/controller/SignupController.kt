package com.ghosttrio.gilbadak.user.controller

import com.ghosttrio.gilbadak.user.controller.model.request.SignupRequest
import com.ghosttrio.gilbadak.user.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class SignupController(
    private val authService: AuthService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): String {
        authService.signup(request.convert())
        return "ok"
    }
}