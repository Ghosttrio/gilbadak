package com.ghosttrio.gilbadak.schedule.controller

import com.ghosttrio.gilbadak.user.service.UserService
import org.springframework.stereotype.Controller

@Controller
class ScheduleController(private val userService: UserService) {

//    @QueryMapping
//    fun loadAllUser(): List<UserEntity> {
//        return userService.loadAllUser()
//    }
//
//    @QueryMapping
//    fun loadUser(@Argument id: Long): UserEntity {
//        return userService.loadUser(id)
//    }
//
//    @MutationMapping
//    fun createUser(@Argument request: UserRequest.Create): String {
//        userService.createUser(request.email, request.nickname)
//        return "성공"
//    }
//
//    @MutationMapping
//    fun updateNickname(@Argument id: Long,
//                       @Argument nickname: String): String {
//        userService.updateNickname(id, nickname)
//        return "성공"
//    }
//
//    @MutationMapping
//    fun deleteUser(@Argument id: Long): String {
//        userService.deleteUser(id)
//        return "성공"
//    }
}