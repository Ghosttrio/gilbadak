package com.ghosttrio.gilbadak.club.repository.persistence

import com.ghosttrio.gilbadak.club.entity.club.ClubJoinState
import com.ghosttrio.gilbadak.club.entity.club.ClubUserDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubUserEntity
import com.ghosttrio.gilbadak.club.mapper.ClubMapper
import com.ghosttrio.gilbadak.club.repository.persistence.repository.ClubUserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.util.*

@Component
class ClubUserPersistenceAdapter(
    private val clubUserRepository: ClubUserRepository,
    private val clubMapper: ClubMapper
) {

    @Transactional
    fun save(clubUserDomain: ClubUserDomain) {
        val clubUserEntity = clubMapper.toEntity(clubUserDomain)
        clubUserRepository.save(clubUserEntity)
    }

    fun findByUserIdAndClubId(userId: Long, clubId: Long): Optional<ClubUserDomain> {
        val clubUserEntity = clubUserRepository.findByUserIdAndClubId(userId, clubId)
        if (clubUserEntity.isPresent) return Optional.of(clubMapper.toDomain(clubUserEntity.get()))
        return Optional.empty()
    }

    fun findByUserIdAndJoinState(userId: Long): Optional<ClubUserDomain> {
        val clubUserEntity = clubUserRepository.findByUserIdAndState(userId, ClubJoinState.REJECT)
        if (clubUserEntity.isPresent) return Optional.of(clubMapper.toDomain(clubUserEntity.get()))
        return Optional.empty()
    }

    fun findAllByClubId(clubId: Long): List<ClubUserDomain> {
        val clubUserEntities = clubUserRepository.findAllByClubId(clubId)
        return clubUserEntities.toClubUserDomainList()
    }

    fun ClubUserEntity.toClubUserDomain(): ClubUserDomain {
        return ClubUserDomain(
            userId = this.userId,
            clubId = this.clubId,
            state = this.state
        )
    }

    fun List<ClubUserEntity>.toClubUserDomainList(): List<ClubUserDomain> {
        return this.map { it.toClubUserDomain() }
    }

    fun ClubUserDomain.toClubUserDomain(): ClubUserEntity {
        return ClubUserEntity(
            id = null,
            userId = this.userId,
            clubId = this.clubId,
            state = this.state
        )
    }

    @Transactional
    fun updateClubUser(clubUserDomain: ClubUserDomain) {
        val clubUserEntity = clubUserDomain.toClubUserDomain()
        clubUserRepository.save(clubUserEntity)
    }
}