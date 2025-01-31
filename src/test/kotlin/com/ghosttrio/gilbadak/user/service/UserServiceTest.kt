//package com.ghosttrio.gilbadak.user.service
//
//import com.ghosttrio.gilbadak.user.infrastructure.UserEntity
//import com.ghosttrio.gilbadak.user.infrastructure.UserRepository
//import io.kotest.core.spec.style.StringSpec
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//
//class UserServiceTest : StringSpec({
//
//    val userRepository = mockk<UserRepository>()
//    val userService = UserService(userRepository)
//
//    "유저의 닉네임은 중복되어서는 안 된다" {
//        val email = "email"
//        val nickname = "nickname"
//
//        every { userRepository.findUserByNickname(nickname) } returns null
////        every { userRepository.save(any()) } returns UserEntity(email, nickname)
//
//        userService.createUser(email, nickname)
//
//        verify { userRepository.save(any()) }
//    }
//
//})