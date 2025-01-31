package com.ghosttrio.gilbadak.club.controller

import com.ghosttrio.gilbadak.club.service.DeleteClubService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/clubs")
class DeleteClubController(
    private val deleteClubService: DeleteClubService
) {
    @DeleteMapping("/{clubId}")
    fun deleteClub(@PathVariable clubId: Long) {
        deleteClubService.deleteClub(clubId)
    }

    @DeleteMapping("/{clubId}/users/{userId}")
    fun kickOutClubUser(@PathVariable clubId: Long, @PathVariable userId: Long) {
        deleteClubService.kickOutClubUser(clubId, userId)
    }
}