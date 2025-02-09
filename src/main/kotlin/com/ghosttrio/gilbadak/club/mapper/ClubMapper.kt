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

    fun toDomain(clubUserEntity: ClubUserEntity): ClubUserDomain {
        return ClubUserDomain(
            userId = clubUserEntity.userId,
            clubId = clubUserEntity.clubId,
            state = clubUserEntity.state
        )
    }

    object Mapper {
        fun ClubEntity.toClubUserDomain(): ClubDomain {
            return ClubDomain(
                name = this.name,
                location = this.location,
                type = this.type,
                description = this.description,
                presidentUserId = this.presentUserId,
            )
        }

        fun List<ClubEntity>.toClubUserDomainList(): List<ClubDomain> {
            return this.map { it.toClubUserDomain() }
        }

    }


}