package com.ghosttrio.gilbadak.user.controller.model.request

class UserRequest {
    data class Create(
        val email: String,
        val password: String,
        val nickname: String
    )
    data class UpdateNickname(
        val nickname: String
    )
}