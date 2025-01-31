package com.ghosttrio.gilbadak.club.controller.model.request

import com.ghosttrio.gilbadak.club.entity.club.ClubJoinState
import com.ghosttrio.gilbadak.club.service.model.request.UpdateApprovalClubJoinStateServiceRequest
import com.ghosttrio.gilbadak.util.ServiceRequestConverter

data class UpdateApprovalClubJoinStateRequest(
    val userId: Long,
    val clubId: Long,
    val state: ClubJoinState
) : ServiceRequestConverter<UpdateApprovalClubJoinStateServiceRequest> {

    override fun convert(): UpdateApprovalClubJoinStateServiceRequest {
        return UpdateApprovalClubJoinStateServiceRequest(userId, clubId, state)
    }
}
