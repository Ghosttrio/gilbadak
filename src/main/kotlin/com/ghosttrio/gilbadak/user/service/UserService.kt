package com.ghosttrio.gilbadak.user.service

import com.ghosttrio.gilbadak.user.infrastructure.UserEntity
import com.ghosttrio.gilbadak.user.infrastructure.UserRepository
import com.ghosttrio.gilbadak.util.ErrorCode.USER_NICKNAME_DUPLICATED
import com.ghosttrio.gilbadak.util.ErrorCode.USER_NOT_FOUND
import com.ghosttrio.gilbadak.util.GilbadakException
import com.ghosttrio.gilbadak.util.Utils.throwIfPresent
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun createUser(email: String, nickname: String) {
        checkExistNickname(nickname)
//        val userEntity = UserEntity(email, nickname)
//        userRepository.save(userEntity)
    }

    private fun checkExistNickname(nickname: String) {
        val findUserByNickname: UserEntity? = userRepository.findUserByNickname(nickname)
        findUserByNickname.throwIfPresent(GilbadakException(USER_NICKNAME_DUPLICATED))
    }

    fun loadUser(id: Long): UserEntity {
        return userRepository.findByIdOrNull(id) ?: throw GilbadakException(USER_NOT_FOUND)
    }

    fun loadAllUser(): List<UserEntity> {
        return userRepository.findAll()
    }

    @Transactional
    fun updateNickname(id: Long, nickname: String) {
        val userEntity = loadUser(id)
//        userEntity.changeNickname(nickname)
    }

    @Transactional
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}