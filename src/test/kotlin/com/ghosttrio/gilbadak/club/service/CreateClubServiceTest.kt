package com.ghosttrio.gilbadak.club.service

import com.ghosttrio.gilbadak.club.entity.club.ClubEntity
import com.ghosttrio.gilbadak.club.entity.club.ClubJoinState
import com.ghosttrio.gilbadak.club.entity.club.ClubType
import com.ghosttrio.gilbadak.club.entity.club.ClubUserDomain
import com.ghosttrio.gilbadak.club.repository.ClubPersistenceAdapter
import com.ghosttrio.gilbadak.club.repository.ClubRepository
import com.ghosttrio.gilbadak.club.repository.ClubUserPersistenceAdapter
import com.ghosttrio.gilbadak.club.repository.ClubUserRepository
import com.ghosttrio.gilbadak.club.service.model.request.CreateClubJoinServiceRequest
import com.ghosttrio.gilbadak.club.service.model.request.CreateClubServiceRequest
import com.ghosttrio.gilbadak.user.domain.UserStatus
import com.ghosttrio.gilbadak.user.infrastructure.UserEntity
import com.ghosttrio.gilbadak.user.infrastructure.UserRepository
import com.ghosttrio.gilbadak.util.GilbadakException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import java.util.*

class CreateClubServiceTest : DescribeSpec({

    val clubPersistenceAdapter = mockk<ClubPersistenceAdapter>()
    val clubUserPersistenceAdapter = mockk<ClubUserPersistenceAdapter>()
    val clubRepository = mockk<ClubRepository>()
    val userRepository = mockk<UserRepository>()
    val createClubService = CreateClubService(
        clubPersistenceAdapter,
        clubUserPersistenceAdapter,
        clubRepository,
        userRepository
    )

    describe("동아리 생성 서비스 테스트") {

        beforeEach {
            clearMocks(clubRepository, userRepository, clubPersistenceAdapter)
        }

        val request = CreateClubServiceRequest(
            name = "Test Club",
            location = "Seoul",
            clubType = ClubType.ART,
            description = "Test club description",
            presidentUserId = 1L
        )

        val clubEntity = ClubEntity(1L, "", "", ClubType.ART, "", 1L)
        val userEntity = UserEntity(null, "email", "nick", UserStatus.ACTIVATED)

        it("동아리 생성 어댑터 호출이 성공해야 한다.") {
            every { clubRepository.findByName(request.name) } returns Optional.empty()
            every { clubRepository.findByPresentUserId(request.presidentUserId) } returns Optional.empty()
            every { userRepository.findById(request.presidentUserId) } returns Optional.empty()
            justRun { clubPersistenceAdapter.save(any()) }

            createClubService.createClub(request)

            verify { clubPersistenceAdapter.save(any()) }
        }

        it("동아리 생성 유저가 존재하지 않는 경우 에러가 발생한다.") {
            every { userRepository.findById(request.presidentUserId) } returns Optional.of(userEntity)
            shouldThrow<GilbadakException> {
                createClubService.createClub(request)
            }.message shouldBe "해당 회원 정보를 찾을 수 없습니다."
        }

        it("동아리 생성 요청 유저가 이미 동아리를 만들었을 경우 에러가 발생한다.") {
            every { userRepository.findById(request.presidentUserId) } returns Optional.empty()
            every { clubRepository.findByPresentUserId(request.presidentUserId) } returns Optional.of(clubEntity)
            shouldThrow<GilbadakException> {
                createClubService.createClub(request)
            }.message shouldBe "유저는 하나의 동아리만 생성할 수 있습니다."
        }

        it("동아리 이름이 중복될 경우 에러가 발생한다.") {
            every { userRepository.findById(request.presidentUserId) } returns Optional.empty()
            every { clubRepository.findByPresentUserId(request.presidentUserId) } returns Optional.empty()
            every { clubRepository.findByName(request.name) } returns Optional.of(clubEntity)
            shouldThrow<GilbadakException> {
                createClubService.createClub(request)
            }.message shouldBe "중복된 동아리 이름입니다."
        }

        it("동아리 가입 신청이 성공해야 한다") {
            every { clubUserPersistenceAdapter.findByUserIdAndClubId(any(), any()) } returns Optional.empty()
            every { clubUserPersistenceAdapter.findByUserIdAndJoinState(any()) } returns Optional.empty()
            every { clubRepository.findById(any()) } returns Optional.empty()
            justRun { clubUserPersistenceAdapter.save(any()) }
            val joinServiceRequest = CreateClubJoinServiceRequest(1L, 2L)

            createClubService.createClubJoinRequest(joinServiceRequest)

            verify { clubUserPersistenceAdapter.save(any()) }
        }

        it("동아리 가입 신청시 이미 해당 유저가 동아리에 가입했다면 에러가 발생한다.") {
            val joinServiceRequest = CreateClubJoinServiceRequest(1L, 2L)
            val clubUserDomain = ClubUserDomain(1L, 2L, ClubJoinState.APPROVAL)
            every { clubUserPersistenceAdapter.findByUserIdAndClubId(any(), any()) } returns Optional.of(clubUserDomain)

            shouldThrow<GilbadakException> {
                createClubService.createClubJoinRequest(joinServiceRequest)
            }.message shouldBe "이미 가입한 동아리입니다."
        }

        it("동아리 가입 신청시 이미 해당 유저가 가입 거절 상태라면 에러가 발생한다.") {
            val clubUserDomain = ClubUserDomain(1L, 2L, ClubJoinState.APPROVAL)
            every { clubUserPersistenceAdapter.findByUserIdAndClubId(any(), any()) } returns Optional.empty()
            every { clubUserPersistenceAdapter.findByUserIdAndJoinState(any()) } returns Optional.of(clubUserDomain)
            val joinServiceRequest = CreateClubJoinServiceRequest(1L, 2L)

            shouldThrow<GilbadakException> {
                createClubService.createClubJoinRequest(joinServiceRequest)
            }.message shouldBe "이미 가입이 거절된 유저입니다."
        }

        it("동아리 가입 신청시 해당 동아리 이름이 중복이라면 에러가 발생한다.") {
            every { clubUserPersistenceAdapter.findByUserIdAndClubId(any(), any()) } returns Optional.empty()
            every { clubUserPersistenceAdapter.findByUserIdAndJoinState(any()) } returns Optional.empty()
            every { clubRepository.findById(any()) } returns Optional.of(clubEntity)
            val joinServiceRequest = CreateClubJoinServiceRequest(1L, 2L)

            shouldThrow<GilbadakException> {
                createClubService.createClubJoinRequest(joinServiceRequest)
            }.message shouldBe "중복된 동아리 이름입니다."
        }
    }

})
