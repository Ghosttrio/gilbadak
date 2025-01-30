package com.ghosttrio.gilbadak.auth.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ghosttrio.gilbadak.auth.controller.model.request.SignupRequest
import com.ghosttrio.gilbadak.auth.service.AuthService
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.nio.charset.Charset

class SignupControllerTest : DescribeSpec({

    val baseUrl = "/api/v1/auth"
    val authService: AuthService = mockk()
    val controller = SignupController(authService)
    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    val objectMapper = ObjectMapper()

    describe("회원가입 테스트") {
        it("회원가입이 성공해야 한다.") {
            // given
            val request = SignupRequest(
                email = "test@email.com",
                name = "name",
                phone = "phone",
                age = "20",
                admissionYear = "2015"
            )

            // when
            val result = mockMvc.perform(
                post("$baseUrl/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk)
                .andReturn()

            // then
            result.response.getContentAsString(Charset.forName("UTF-8")) shouldBe "ok"

        }
    }
})