package com.ghosttrio.gilbadak.club.controller.model.request

import com.ghosttrio.gilbadak.club.service.model.request.UpdateClubInformationServiceRequest
import com.ghosttrio.gilbadak.util.ServiceRequestConverter

data class UpdateClubInformationRequest(
    val name: String,
    val description: String
) : ServiceRequestConverter<UpdateClubInformationServiceRequest> {
    override fun convert(): UpdateClubInformationServiceRequest {
        return UpdateClubInformationServiceRequest(name, description)
    }
}
