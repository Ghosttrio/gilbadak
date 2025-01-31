package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubInformation
import com.ghosttrio.gilbadak.club.entity.club.ClubJoinRequest
import org.springframework.stereotype.Service

@Service
class LoadClubService {

    fun loadAllClubs(): List<ClubDomain> {
        return listOf()
    }

    fun loadClub(): ClubDomain {
        return ClubDomain()
    }

    fun loadClubSecretInformation(clubId: Long, userId: Long): ClubInformation {
        return ClubInformation()
    }

    fun loadAllClubJoinRequests(clubId: Long): List<ClubJoinRequest> {
        return listOf()
    }

}