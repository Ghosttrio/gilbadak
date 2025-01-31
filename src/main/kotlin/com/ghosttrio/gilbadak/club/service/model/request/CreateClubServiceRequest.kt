package com.ghosttrio.gilbadak.club.service.model.request

data class CreateClubServiceRequest(
    val name: String,
    val presidentUserId: Long,
    val location: String,
    val description: String
) {
}