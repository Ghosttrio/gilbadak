package com.ghosttrio.gilbadak.club.repository

import com.ghosttrio.gilbadak.club.entity.club.ClubUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import javax.swing.text.html.Option

interface ClubUserRepository : JpaRepository<ClubUserEntity, Long> {
    fun findByUserId(userId: Long): Optional<ClubUserEntity>

    fun findByUserIdAndClubId(userId: Long, clubId: Long): Optional<ClubUserEntity>
}