package com.ghosttrio.gilbadak.club.controller

import com.ghosttrio.gilbadak.club.controller.model.request.UpdateApprovalClubJoinStateRequest
import com.ghosttrio.gilbadak.club.controller.model.request.UpdateClubInformationRequest
import com.ghosttrio.gilbadak.club.service.UpdateClubService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/clubs")
class UpdateClubController(
    private val updateClubService: UpdateClubService
) {

    @PatchMapping("/approval")
    fun updateApprovalClubJoinState(@RequestBody request: UpdateApprovalClubJoinStateRequest) {
        updateClubService.updateApprovalClubJoinState(request.convert())
    }

    @PatchMapping
    fun updateClubInformation(@RequestBody request: UpdateClubInformationRequest) {
        updateClubService.updateClubInformation(request.convert())
    }
}