package com.ghosttrio.gilbadak.user.repository

import com.ghosttrio.gilbadak.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long>, UserQueryRepository {
}