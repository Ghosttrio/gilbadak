package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.service.model.request.UpdateApprovalClubJoinStateServiceRequest
import com.ghosttrio.gilbadak.club.service.model.request.UpdateClubInformationServiceRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UpdateClubService {

    @Transactional
    fun updateApprovalClubJoinState(request: UpdateApprovalClubJoinStateServiceRequest) {

    }

    @Transactional
    fun updateClubInformation(request: UpdateClubInformationServiceRequest) {

    }
}