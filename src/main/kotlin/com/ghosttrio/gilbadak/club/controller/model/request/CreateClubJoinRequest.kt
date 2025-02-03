package com.ghosttrio.gilbadak.club.controller.model.request

import com.ghosttrio.gilbadak.club.service.model.request.CreateClubJoinServiceRequest
import com.ghosttrio.gilbadak.util.ServiceRequestConverter

data class CreateClubJoinRequest(
    val userId: Long,
    val clubId: Long
) : ServiceRequestConverter<CreateClubJoinServiceRequest> {
    override fun convert(): CreateClubJoinServiceRequest {
        return CreateClubJoinServiceRequest(userId, clubId)
    }
}