package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.repository.persistence.repository.ClubRepository
import com.ghosttrio.gilbadak.club.repository.persistence.repository.ClubUserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DeleteClubService(
    private val clubRepository: ClubRepository,
    private val clubUserRepository: ClubUserRepository
) {

    // todo 삭제에 유예기간 주기 구현, 스케줄러로 하면 될 듯?
    @Transactional
    fun deleteClub(clubId: Long) {
        clubRepository.deleteById(clubId)
    }

    @Transactional
    fun kickOutClubUser(clubId: Long, userId: Long) {
        clubUserRepository.deleteByClubIdAndUserId(clubId, userId)
    }
}