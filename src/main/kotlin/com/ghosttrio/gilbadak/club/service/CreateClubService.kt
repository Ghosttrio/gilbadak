package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.service.model.request.CreateClubJoinServiceRequest
import com.ghosttrio.gilbadak.club.service.model.request.CreateClubServiceRequest
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateClubService {

    @Transactional
    fun createClub(request: CreateClubServiceRequest) {

    }

    @Transactional
    fun createClubJoinRequest(request: CreateClubJoinServiceRequest) {

    }
}