package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubUserDomain
import com.ghosttrio.gilbadak.club.repository.ClubPersistenceAdapter
import com.ghosttrio.gilbadak.club.repository.ClubRepository
import com.ghosttrio.gilbadak.club.repository.ClubUserPersistenceAdapter
import com.ghosttrio.gilbadak.club.repository.ClubUserRepository
import com.ghosttrio.gilbadak.club.service.model.request.CreateClubJoinServiceRequest
import com.ghosttrio.gilbadak.club.service.model.request.CreateClubServiceRequest
import com.ghosttrio.gilbadak.user.infrastructure.UserRepository
import com.ghosttrio.gilbadak.util.ErrorCode.*
import com.ghosttrio.gilbadak.util.GilbadakException
import jakarta.transaction.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CreateClubService(
    private val clubPersistenceAdapter: ClubPersistenceAdapter,
    private val clubUserPersistenceAdapter: ClubUserPersistenceAdapter,
    private val clubRepository: ClubRepository,
    private val userRepository: UserRepository
) {

    fun createClub(request: CreateClubServiceRequest) {
        validateExistUser(request.presidentUserId)
        validateDuplicatePresidentUser(request.presidentUserId)
        validateDuplicateClubName(request.name)

        val clubDomain = ClubDomain.create(
            request.name,
            request.location,
            request.clubType,
            request.description,
            request.presidentUserId
        )
        clubPersistenceAdapter.save(clubDomain)
    }

    private fun validateExistUser(userId: Long) {
        val isExist = userRepository.findById(userId).isEmpty
        if (!isExist) throw GilbadakException(USER_NOT_FOUND)
    }

    private fun validateDuplicatePresidentUser(userId: Long) {
        val isDuplicate = clubRepository.findByPresentUserId(userId).isPresent
        if (isDuplicate) throw GilbadakException(CLUB_PRESIDENT_DUPLICATED)
    }

    private fun validateDuplicateClubName(name: String) {
        val isDuplicate = clubRepository.findByName(name).isPresent
        if (isDuplicate) throw GilbadakException(CLUB_NAME_DUPLICATED)
    }

    @Transactional
    fun createClubJoinRequest(request: CreateClubJoinServiceRequest) {
        validateAlreadyExistClubUser(request.userId, request.clubId)
        validateRejectedUser(request.userId)
        validateExistClubName(request.clubId)
        val clubUserDomain = ClubUserDomain.create(request.userId, request.clubId)
        clubUserPersistenceAdapter.save(clubUserDomain)
    }

    private fun validateAlreadyExistClubUser(userId: Long, clubId: Long) {
        val isExist = clubUserPersistenceAdapter.findByUserIdAndClubId(userId, clubId).isPresent
        if (isExist) throw GilbadakException(CLUB_ALREADY_EXIST)
    }

    private fun validateRejectedUser(userId: Long) {
        val isExist = clubUserPersistenceAdapter.findByUserIdAndJoinState(userId).isPresent
        if (isExist) throw GilbadakException(CLUB_REJECTED)
    }

    private fun validateExistClubName(clubId: Long) {
        val isExist = clubRepository.findById(clubId).isPresent
        if (isExist) throw GilbadakException(CLUB_NAME_DUPLICATED)
    }
}