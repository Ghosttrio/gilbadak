package com.ghosttrio.gilbadak.util

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원 정보를 찾을 수 없습니다."),
    USER_NICKNAME_DUPLICATED(HttpStatus.BAD_REQUEST, "해당 닉네임이 이미 존재합니다.");
}