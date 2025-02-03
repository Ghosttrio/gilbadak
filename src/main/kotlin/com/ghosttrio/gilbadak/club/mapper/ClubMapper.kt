package com.ghosttrio.gilbadak.club.mapper

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubEntity
import com.ghosttrio.gilbadak.club.entity.club.ClubUserDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubUserEntity
import org.springframework.stereotype.Component

@Component
class ClubMapper {

    fun toEntity(clubDomain: ClubDomain): ClubEntity {
        return ClubEntity(
            id = null,
            name = clubDomain.name,
            location = clubDomain.location,
            type = clubDomain.type,
            description = clubDomain.description,
            presentUserId = clubDomain.presidentUserId,
        )
    }

    fun toEntity(clubUserDomain: ClubUserDomain): ClubUserEntity {
        return ClubUserEntity(
            id = null,
            userId = clubUserDomain.userId,
            clubId = clubUserDomain.clubId,
            state = clubUserDomain.state
        )
    }
}