package com.ghosttrio.gilbadak.user.repository

import com.ghosttrio.gilbadak.user.entity.UserEntity

interface UserQueryRepository {
    fun findUserByNickname(nickname: String): UserEntity?
}
