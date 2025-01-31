package com.ghosttrio.gilbadak.club.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DeleteClubService {

    @Transactional
    fun deleteClub(clubId: Long) {
        // todo 삭제에 유예기간 주기 구현
    }

    @Transactional
    fun kickOutClubUser(clubId: Long, userId: Long) {

    }
}