package com.ghosttrio.gilbadak.user.infrastructure

import com.ghosttrio.gilbadak.user.infrastructure.QUserEntity.userEntity
import com.querydsl.jpa.impl.JPAQueryFactory

class UserQueryRepositoryImpl(
    private val query: JPAQueryFactory
) : UserQueryRepository {

    override fun findUserByNickname(nickname: String): UserEntity? {
        return query.selectFrom(userEntity)
            .where(userEntity.nickname.eq(nickname))
            .fetchOne()
    }

    override fun findByEmail(email: String): UserEntity? {
        return query.selectFrom(userEntity)
            .where(userEntity.email.eq(email))
            .fetchOne()
    }
}
