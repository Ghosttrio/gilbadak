package com.ghosttrio.gilbadak.club.repository

import com.ghosttrio.gilbadak.club.entity.club.ClubUserDomain
import com.ghosttrio.gilbadak.club.mapper.ClubMapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

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

    fun findByUserIdAndClubId(userId: Long, clubId: Long){
        clubUserRepository.findByUserIdAndClubId(userId, clubId)
    }
}