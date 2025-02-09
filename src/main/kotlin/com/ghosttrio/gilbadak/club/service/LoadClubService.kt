package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubJoinResponse
import com.ghosttrio.gilbadak.club.entity.club.ClubUserDomain
import com.ghosttrio.gilbadak.club.repository.persistence.ClubPersistenceAdapter
import com.ghosttrio.gilbadak.club.repository.persistence.ClubUserPersistenceAdapter
import org.springframework.stereotype.Service

@Service
class LoadClubService(
    private val clubPersistenceAdapter: ClubPersistenceAdapter,
    private val clubUserPersistenceAdapter: ClubUserPersistenceAdapter
) {

    fun loadAllClubs(): List<ClubDomain> {
        return clubPersistenceAdapter.findAll()
    }

    fun loadClub(clubId: Long): ClubDomain {
        return clubPersistenceAdapter.findById(clubId)
    }

    fun loadAllClubJoinRequests(clubId: Long): List<ClubUserDomain> {
        return clubUserPersistenceAdapter.findAllByClubId(clubId)
    }

}