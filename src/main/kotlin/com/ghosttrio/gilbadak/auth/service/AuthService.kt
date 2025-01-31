package com.ghosttrio.gilbadak.auth.service

import com.ghosttrio.gilbadak.auth.controller.model.request.SignupServiceRequest
import com.ghosttrio.gilbadak.user.domain.UserDomain
import org.springframework.stereotype.Service

@Service
class AuthService {
    fun signup(request: SignupServiceRequest) {
        val userDomain = UserDomain(request.email, request.name, request.phone, request.age, request.admissionYear)
    }
}