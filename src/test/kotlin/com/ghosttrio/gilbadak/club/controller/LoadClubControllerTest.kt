package com.ghosttrio.gilbadak.club.controller

import com.ghosttrio.gilbadak.club.entity.club.ClubDomain
import com.ghosttrio.gilbadak.club.entity.club.ClubInformation
import com.ghosttrio.gilbadak.club.entity.club.ClubJoinRequest
import com.ghosttrio.gilbadak.club.service.LoadClubService
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@AutoConfigureMockMvc
class LoadClubControllerTest : DescribeSpec({

    val loadClubService = mockk<LoadClubService>()
    val loadClubController = LoadClubController(loadClubService)
    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(loadClubController).build()

    describe("동아리 조회 API 테스트") {
        it("모든 동아리 조회가 성공해야 한다") {
            val clubList = listOf(ClubDomain())
            every { loadClubService.loadAllClubs() } returns clubList
            mockMvc.perform(get("/api/v1/clubs"))
                .andExpect(status().isOk)
        }

        it("동아리 상세 정보 조회가 성공해야 한다") {
            val club = ClubDomain()
            every { loadClubService.loadClub() } returns club
            mockMvc.perform(get("/api/v1/clubs/{clubId}", 1L))
                .andExpect(status().isOk)
        }

        it("동아리 비밀 정보 조회가 성공해야 한다") {
            val clubInformation = ClubInformation()
            val clubId = 1L
            val userId = 1L
            every { loadClubService.loadClubSecretInformation(clubId, userId) } returns clubInformation
            mockMvc.perform(get("/api/v1/clubs/{clubId}/users/{userId}", clubId, userId))
                .andExpect(status().isOk)
        }

        it("동아리 가입 신청 목록 조회가 성공해야 한다") {
            val joinRequests = listOf(ClubJoinRequest())
            val clubId = 1L
            every { loadClubService.loadAllClubJoinRequests(clubId) } returns joinRequests
            mockMvc.perform(get("/api/v1/clubs/{clubId}/join", clubId))
                .andExpect(status().isOk)
        }
    }

})
