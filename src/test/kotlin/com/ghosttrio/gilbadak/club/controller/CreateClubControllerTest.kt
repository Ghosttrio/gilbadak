package com.ghosttrio.gilbadak.club.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ghosttrio.gilbadak.club.controller.model.request.CreateClubJoinRequest
import com.ghosttrio.gilbadak.club.controller.model.request.CreateClubRequest
import com.ghosttrio.gilbadak.club.entity.club.ClubType
import com.ghosttrio.gilbadak.club.service.CreateClubService
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.justRun
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@AutoConfigureMockMvc
class CreateClubControllerTest : DescribeSpec({

    val createClubService = mockk<CreateClubService>()
    val createClubController = CreateClubController(createClubService)
    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(createClubController).build()
    val objectMapper = ObjectMapper()

    describe("동아리 생성 API 테스트") {
        it("동아리 생성 요청이 성공해야 한다") {
            val request = CreateClubRequest("name", 1L, "location", "description", ClubType.ART)
            justRun { createClubService.createClub(request.convert()) }

            mockMvc.perform(
                post("/api/v1/clubs")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
                .andExpect(status().isOk)
        }

        it("동아리 가입 요청이 성공해야 한다") {
            val request = CreateClubJoinRequest(1L, 1L)
            justRun { createClubService.createClubJoinRequest(request.convert()) }

            mockMvc.perform(
                post("/api/v1/clubs/join")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
                .andExpect(status().isOk)
        }
    }
})
