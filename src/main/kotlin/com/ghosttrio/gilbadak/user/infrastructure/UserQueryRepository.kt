package com.ghosttrio.gilbadak.user.infrastructure

interface UserQueryRepository {
    fun findUserByNickname(nickname: String): UserEntity?
}
