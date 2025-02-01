package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.repository.ClubPersistenceAdapter
import com.ghosttrio.gilbadak.club.repository.ClubRepository
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
    private val clubRepository: ClubRepository,
    private val userRepository: UserRepository
) {

    fun createClub(request: CreateClubServiceRequest) {
        validateExistPresidentUser(request.presidentUserId)
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

    private fun validateExistPresidentUser(userId: Long) {
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

    }
}