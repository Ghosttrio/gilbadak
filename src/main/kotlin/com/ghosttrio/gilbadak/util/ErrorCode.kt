package com.ghosttrio.gilbadak.util

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원 정보를 찾을 수 없습니다."),
    USER_NICKNAME_DUPLICATED(HttpStatus.BAD_REQUEST, "해당 닉네임이 이미 존재합니다."),
    CLUB_PRESIDENT_DUPLICATED(HttpStatus.BAD_REQUEST, "유저는 하나의 동아리만 생성할 수 있습니다."),
    CLUB_NAME_DUPLICATED(HttpStatus.BAD_REQUEST, "중복된 동아리 이름입니다."),
    CLUB_REJECTED(HttpStatus.BAD_REQUEST, "이미 가입이 거절된 유저입니다."),
    CLUB_NOT_FOUNT(HttpStatus.NOT_FOUND, "해당 동아리를 찾을 수 없습니다.")
    ;

}