package com.ghosttrio.gilbadak.club.service.model.request

import com.ghosttrio.gilbadak.club.entity.club.ClubJoinState

data class UpdateApprovalClubJoinStateServiceRequest(
    val userId: Long,
    val clubId: Long,
    val state: ClubJoinState
)
