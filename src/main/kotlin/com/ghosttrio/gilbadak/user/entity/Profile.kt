package com.ghosttrio.gilbadak.user.entity

import jakarta.persistence.Embeddable

@Embeddable
class Profile(
    val email: String,
    var nickname: String
) {

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }
}