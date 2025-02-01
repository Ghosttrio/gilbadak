package com.ghosttrio.gilbadak.club.mapper

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubEntity
import org.springframework.stereotype.Component

@Component
class ClubMapper {

    fun toEntity(clubDomain: ClubDomain) : ClubEntity {
        return ClubEntity(
            id  = null,
            name = clubDomain.name,
            location = clubDomain.location,
            type = clubDomain.type,
            description = clubDomain.description,
            presentUserId = clubDomain.presidentUserId,
        )
    }
}