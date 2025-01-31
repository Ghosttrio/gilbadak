package com.ghosttrio.gilbadak.club.controller.model.request

import com.ghosttrio.gilbadak.club.service.model.request.CreateClubServiceRequest
import com.ghosttrio.gilbadak.util.ServiceRequestConverter

data class CreateClubRequest(
    val name: String,
    val presidentUserId: Long,
    val location: String,
    val description: String
) : ServiceRequestConverter<CreateClubServiceRequest> {
    override fun convert(): CreateClubServiceRequest {
        return CreateClubServiceRequest(name, presidentUserId, location, description)
    }
}
