package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.repository.ClubRepository
import com.ghosttrio.gilbadak.club.repository.ClubUserRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc

@AutoConfigureMockMvc
class DeleteClubServiceTest : DescribeSpec({

    val clubRepository = mockk<ClubRepository>()
    val clubUserRepository = mockk<ClubUserRepository>()
    val deleteClubService = DeleteClubService(clubRepository, clubUserRepository)

    describe("동아리 삭제 서비스 테스트") {
        it("동아리 삭제가 성공해야 한다.") {
            justRun { clubRepository.deleteById(any()) }

            deleteClubService.deleteClub(1L)

            verify { clubRepository.deleteById(any()) }
        }

        it("동아리 회원 추방이 성공해야 한다") {
            justRun { clubUserRepository.deleteByClubIdAndUserId(any(), any()) }

            deleteClubService.kickOutClubUser(1L, 2L)

            verify { clubUserRepository.deleteByClubIdAndUserId(any(), any()) }
        }
    }

})
