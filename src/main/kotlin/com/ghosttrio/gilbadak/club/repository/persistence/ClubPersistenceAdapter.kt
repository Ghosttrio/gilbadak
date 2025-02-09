package com.ghosttrio.gilbadak.club.repository.persistence

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.mapper.ClubMapper
import com.ghosttrio.gilbadak.club.mapper.ClubMapper.Mapper.toClubUserDomain
import com.ghosttrio.gilbadak.club.mapper.ClubMapper.Mapper.toClubUserDomainList
import com.ghosttrio.gilbadak.club.repository.persistence.repository.ClubRepository
import com.ghosttrio.gilbadak.util.ErrorCode
import com.ghosttrio.gilbadak.util.GilbadakException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class ClubPersistenceAdapter(
    private val clubRepository: ClubRepository,
    private val clubMapper: ClubMapper
) {

    @Transactional
    fun save(clubDomain: ClubDomain) {
        val clubEntity = clubMapper.toEntity(clubDomain)
        clubRepository.save(clubEntity)
    }

    fun findAll(): List<ClubDomain> {
        val clubEntities = clubRepository.findAll()
        return clubEntities.toClubUserDomainList()
    }

    fun findById(clubId: Long) : ClubDomain {
        val clubEntity = clubRepository.findById(clubId)
            .orElseThrow { GilbadakException(ErrorCode.CLUB_NOT_FOUNT) }
        return clubEntity.toClubUserDomain()
    }

    @Transactional
    fun updateClub(clubDomain: ClubDomain) {
        val entity = clubMapper.toEntity(clubDomain)
        clubRepository.save(entity)
    }


}