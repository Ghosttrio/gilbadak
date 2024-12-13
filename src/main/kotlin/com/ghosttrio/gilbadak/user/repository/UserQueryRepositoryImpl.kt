package com.ghosttrio.gilbadak.user.repository

import com.ghosttrio.gilbadak.user.entity.QUserEntity.userEntity
import com.ghosttrio.gilbadak.user.entity.UserEntity
import com.querydsl.jpa.impl.JPAQueryFactory

class UserQueryRepositoryImpl(
    private val query: JPAQueryFactory
) : UserQueryRepository {

    override fun findUserByNickname(nickname: String): UserEntity? {
        return query.selectFrom(userEntity)
            .where(userEntity.nickname.eq(nickname))
            .fetchOne()
    }
}
