package com.ghosttrio.gilbadak.user.controller

import com.ghosttrio.gilbadak.user.controller.model.request.UserRequest
import com.ghosttrio.gilbadak.user.entity.UserEntity
import com.ghosttrio.gilbadak.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun loadAllUser(): ResponseEntity<List<UserEntity>> {
        val result = userService.loadAllUser()
        return ResponseEntity.ok(result)
    }

    @PostMapping
    fun createUser(@RequestBody request: UserRequest.Create): ResponseEntity<String> {
        userService.createUser(request.email, request.nickname)
        return ResponseEntity.ok("성공")
    }

    @GetMapping("/{id}")
    fun loadUser(@PathVariable id: Long): ResponseEntity<UserEntity> {
        val result = userService.loadUser(id)
        return ResponseEntity.ok(result)
    }

    @PatchMapping("/{id}")
    fun updateNickname(@RequestBody request: UserRequest.UpdateNickname,
                       @PathVariable id: Long): ResponseEntity.BodyBuilder {
        userService.updateNickname(id, request.nickname)
        return ResponseEntity.ok()
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity.BodyBuilder {
        userService.deleteUser(id)
        return ResponseEntity.ok()
    }
}