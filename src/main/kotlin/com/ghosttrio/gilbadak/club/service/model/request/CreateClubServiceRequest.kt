package com.ghosttrio.gilbadak.club.service.model.request

import com.ghosttrio.gilbadak.club.entity.club.ClubType

data class CreateClubServiceRequest(
    val name: String,
    val presidentUserId: Long,
    val location: String,
    val description: String,
    val clubType: ClubType
)