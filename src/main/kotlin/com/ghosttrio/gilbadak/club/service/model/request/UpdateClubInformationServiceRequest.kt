package com.ghosttrio.gilbadak.club.service.model.request

data class UpdateClubInformationServiceRequest(
    val clubId: Long,
    val name: String,
    val description: String
)
