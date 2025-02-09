package com.ghosttrio.gilbadak.club.controller

import com.ghosttrio.gilbadak.club.service.DeleteClubService
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.justRun
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@AutoConfigureMockMvc
class DeleteClubControllerTest : DescribeSpec({

    val deleteClubService = mockk<DeleteClubService>()
    val deleteClubController = DeleteClubController(deleteClubService)
    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(deleteClubController).build()

    describe("동아리 삭제 API 테스트") {
        it("동아리 삭제 요청이 성공해야 한다") {
            val clubId = 1L
            justRun { deleteClubService.deleteClub(clubId) }

            mockMvc.perform(delete("/api/v1/clubs/{clubId}", clubId))
                .andExpect(status().isOk)
        }

        it("동아리원 추방 요청이 성공해야 한다") {
            val clubId = 1L
            val userId = 1L
            justRun { deleteClubService.kickOutClubUser(clubId, userId) }

            mockMvc.perform(delete("/api/v1/clubs/{clubId}/users/{userId}", clubId, userId))
                .andExpect(status().isOk)
        }

    }
})
