package com.ghosttrio.gilbadak.club.entity.club

import com.ghosttrio.gilbadak.util.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "tb_club_user")
class ClubUserEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long?,
    val userId: Long,
    val clubId: Long,
    val state: ClubJoinState
) : BaseTimeEntity()