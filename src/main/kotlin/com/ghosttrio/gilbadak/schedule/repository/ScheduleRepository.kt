package com.ghosttrio.gilbadak.schedule.repository

import com.ghosttrio.gilbadak.schedule.entity.Schedule
import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository: JpaRepository<Schedule, Long> {
}