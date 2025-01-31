package com.ghosttrio.gilbadak.club.controller

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubInformation
import com.ghosttrio.gilbadak.club.entity.club.ClubJoinRequest
import com.ghosttrio.gilbadak.club.service.LoadClubService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/clubs")
class LoadClubController(
    private val loadClubService: LoadClubService
) {

    @GetMapping
    fun loadAllClubs(): List<ClubDomain> {
        val result = loadClubService.loadAllClubs()
        return result
    }

    @GetMapping("/{clubId}")
    fun loadClub(@PathVariable clubId: Long): ClubDomain {
        val result = loadClubService.loadClub()
        return result
    }

    @GetMapping("/{clubId}/users/{userId}")
    fun loadClubSecretInformation(
        @PathVariable clubId: Long,
        @PathVariable userId: Long
    ): ClubInformation {
        val result = loadClubService.loadClubSecretInformation(clubId, userId)
        return result
    }

    @GetMapping("/{clubId}/join")
    fun loadAllClubJoinRequests(@PathVariable clubId: Long): List<ClubJoinRequest> {
        val result = loadClubService.loadAllClubJoinRequests(clubId)
        return result
    }
}