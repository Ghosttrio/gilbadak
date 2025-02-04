package com.ghosttrio.gilbadak.club.repository

import com.ghosttrio.gilbadak.club.entity.club.ClubJoinState
import com.ghosttrio.gilbadak.club.entity.club.ClubUserDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubUserEntity
import com.ghosttrio.gilbadak.club.mapper.ClubMapper
import com.ghosttrio.gilbadak.util.ErrorCode
import com.ghosttrio.gilbadak.util.GilbadakException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.util.*
import javax.swing.text.html.Option

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
}