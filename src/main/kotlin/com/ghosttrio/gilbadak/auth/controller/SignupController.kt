package com.ghosttrio.gilbadak.auth.controller

import com.ghosttrio.gilbadak.auth.controller.model.request.SignupRequest
import com.ghosttrio.gilbadak.auth.service.AuthService
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

        return "ok"
    }
}