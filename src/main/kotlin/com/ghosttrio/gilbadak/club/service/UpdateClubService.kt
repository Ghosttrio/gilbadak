package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.repository.persistence.ClubPersistenceAdapter
import com.ghosttrio.gilbadak.club.repository.persistence.ClubUserPersistenceAdapter
import com.ghosttrio.gilbadak.club.service.model.request.UpdateApprovalClubJoinStateServiceRequest
import com.ghosttrio.gilbadak.club.service.model.request.UpdateClubInformationServiceRequest
import com.ghosttrio.gilbadak.util.ErrorCode.CLUB_NOT_FOUNT
import com.ghosttrio.gilbadak.util.GilbadakException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UpdateClubService(
    private val clubPersistenceAdapter: ClubPersistenceAdapter,
    private val clubUserPersistenceAdapter: ClubUserPersistenceAdapter
) {

    @Transactional
    fun updateApprovalClubJoinState(request: UpdateApprovalClubJoinStateServiceRequest) {
        val clubUserDomain = clubUserPersistenceAdapter.findByUserIdAndClubId(request.userId, request.clubId)
            .orElseThrow { GilbadakException(CLUB_NOT_FOUNT) }
        val result = clubUserDomain.approvalState()
        clubUserPersistenceAdapter.updateClubUser(result)
    }

    @Transactional
    fun updateClubInformation(request: UpdateClubInformationServiceRequest) {
        val clubDomain = clubPersistenceAdapter.findById(request.clubId)
        val result = clubDomain.updateClubInformation(request.name, request.description)
        clubPersistenceAdapter.updateClub(result)
    }
}