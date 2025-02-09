package com.ghosttrio.gilbadak.club.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ghosttrio.gilbadak.club.controller.model.request.UpdateApprovalClubJoinStateRequest
import com.ghosttrio.gilbadak.club.controller.model.request.UpdateClubInformationRequest
import com.ghosttrio.gilbadak.club.entity.club.ClubJoinState
import com.ghosttrio.gilbadak.club.service.UpdateClubService
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.justRun
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@AutoConfigureMockMvc
class UpdateClubControllerTest : DescribeSpec({

    val updateClubService = mockk<UpdateClubService>()
    val updateClubController = UpdateClubController(updateClubService)
    val mockMvc = MockMvcBuilders.standaloneSetup(updateClubController).build()
    val objectMapper = ObjectMapper()

    describe("동아리 수정 API 테스트") {
        it("동아리 가입 승인 / 거절 요청이 성공해야 한다.") {
            val request = UpdateApprovalClubJoinStateRequest(1L, 2L, ClubJoinState.APPROVAL)
            justRun { updateClubService.updateApprovalClubJoinState(request.convert()) }
            mockMvc.perform(patch("/api/v1/clubs/approval")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk)
        }
        it ("동아리 정보 수정 요청이 성공해야 한다.") {
            val request = UpdateClubInformationRequest(1L, "name", "description")
            justRun { updateClubService.updateClubInformation(request.convert()) }
            mockMvc.perform(patch("/api/v1/clubs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk)
        }
    }

})
