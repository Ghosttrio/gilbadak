package com.ghosttrio.gilbadak.auth.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class SigninController {

    @PostMapping("/signin")
    fun signin() {

    }
}