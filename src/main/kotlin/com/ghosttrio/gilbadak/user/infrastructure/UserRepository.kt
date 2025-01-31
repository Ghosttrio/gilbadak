package com.ghosttrio.gilbadak.user.infrastructure

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long>, UserQueryRepository {
}