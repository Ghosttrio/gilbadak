package com.ghosttrio.gilbadak.club.repository

import com.ghosttrio.gilbadak.club.entity.club.ClubEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClubRepository : JpaRepository<ClubEntity, Long> {

    fun findByPresentUserId(userId: Long) : Optional<ClubEntity>

    fun findByName(clubName: String) : Optional<ClubEntity>
}