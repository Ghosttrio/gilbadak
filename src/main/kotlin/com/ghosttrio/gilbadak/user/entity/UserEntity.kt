package com.ghosttrio.gilbadak.user.entity

import com.ghosttrio.gilbadak.util.BaseTimeEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "tb_users")
class UserEntity(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long?,
    val email: String,
    var nickname: String,
    @Enumerated(EnumType.STRING)
    var status: UserStatus
): BaseTimeEntity() {

//    constructor() : this(null, "", "") JPA Gradle 플러그인으로 제거
    constructor(email: String, nickname: String) : this(null, email, nickname, UserStatus.ACTIVATED)

    fun updateNickname(nickname: String) {
        this.nickname = nickname
    }
}