package com.ghosttrio.gilbadak.club.entity.club

import com.ghosttrio.gilbadak.util.BaseTimeEntity
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "tb_clubs")
class ClubEntity(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long?,
    val name: String,
    val location: String,
    @Enumerated(STRING)
    val type: ClubType,
    val description: String,
    val presentUserId: Long
): BaseTimeEntity()