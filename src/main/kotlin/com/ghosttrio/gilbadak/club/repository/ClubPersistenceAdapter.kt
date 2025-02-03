package com.ghosttrio.gilbadak.club.repository

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.mapper.ClubMapper
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

}