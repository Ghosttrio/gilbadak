package com.ghosttrio.gilbadak.user.service

import com.ghosttrio.gilbadak.security.JwtGenerator
import com.ghosttrio.gilbadak.security.JwtToken
import com.ghosttrio.gilbadak.user.controller.model.request.SigninRequest
import com.ghosttrio.gilbadak.user.controller.model.request.SignupServiceRequest
import com.ghosttrio.gilbadak.user.domain.UserDomain
import com.ghosttrio.gilbadak.user.infrastructure.UserRepository
import com.ghosttrio.gilbadak.util.ErrorCode.USER_NOT_FOUND
import com.ghosttrio.gilbadak.util.GilbadakException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtGenerator: JwtGenerator
) {
    fun signup(request: SignupServiceRequest) {
        val userDomain = UserDomain(request.email, request.name, request.phone, request.age, request.admissionYear)
    }

    fun signin(request: SigninRequest): JwtToken {
        val userEntity = userRepository.findByEmail(request.userId) ?: throw GilbadakException(USER_NOT_FOUND)
        return jwtGenerator.createToken(userEntity.id, userEntity.email, userEntity.role)
        // todo redis 저장 로직
    }
}