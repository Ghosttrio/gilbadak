package com.ghosttrio.gilbadak.service

import com.ghosttrio.gilbadak.user.service.UserService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk


class UserServiceTest : BehaviorSpec({

    // UserRepository를 모킹
    val userRepository = mockk<UserRepository>(relaxed = true)
    val userService = UserService(userRepository)

    Given("닉네임이 이미 존재하는 경우") {
        val existingNickname = "existingNickname"

        // userRepository.findUserByNickname이 존재하는 닉네임을 반환하도록 설정
        every { userRepository.findUserByNickname(existingNickname) } returns UserEntity (
            1,
            "test@example.com",
            existingNickname,
            ACTIVATED
        )

        When("createUser가 호출되면") {
            Then("IllegalStateException이 발생해야 한다") {
                shouldThrow<IllegalStateException> {
                    userService.createUser("test@example.com", existingNickname)
                }.message shouldBe "이미 존재하는 닉네임입니다."
            }
        }
    }

    Given("닉네임이 존재하지 않는 경우") {
        val newNickname = "newNickname"
        // userRepository.findUserByNickname이 null을 반환하도록 설정
        every { userRepository.findUserByNickname(newNickname) } returns null

        When("createUser가 호출되면") {
            Then("사용자가 저장되어야 한다") {
                val user = UserEntity(
                    "email", "nickname"
                )
                every { userRepository.save(user) } shouldNotBe null
            }
        }
    }
})