package com.ghosttrio.gilbadak.schedule.entity

import com.ghosttrio.gilbadak.util.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_schedules")
class Schedule(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long,
) : BaseTimeEntity() {
}