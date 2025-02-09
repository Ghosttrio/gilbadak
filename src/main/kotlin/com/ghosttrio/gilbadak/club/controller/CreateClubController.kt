package com.ghosttrio.gilbadak.club.controller

import com.ghosttrio.gilbadak.club.controller.model.request.CreateClubJoinRequest
import com.ghosttrio.gilbadak.club.controller.model.request.CreateClubRequest
import com.ghosttrio.gilbadak.club.service.CreateClubService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/clubs")
class CreateClubController(
    private val createClubService: CreateClubService
) {

    @PostMapping
    fun createClub(@RequestBody request: CreateClubRequest) {
        createClubService.createClub(request.convert())
    }
    
    @PostMapping("/join")
    fun createClubJoinRequest(@RequestBody request: CreateClubJoinRequest) {
        createClubService.createClubJoinRequest(request.convert())
    }
}