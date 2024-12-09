package com.ghosttrio.gilbadak.club.entity.club

enum class ClubType(
    val type: String
) {
    COLLEGE("단과대학 동아리"),
    TEMP("가등록 동아리"),
    SPORT("체육분과"),
    ART("전시분과"),
    RELIGION("종교분과"),
    ACADEMIC("학술분과"),
    VOLUNTEER("봉사분과"),
    LANGUAGE("어학분과"),
    STARTUP("취/창업분과"),
    PERFORMANCE("공연분과"),
    ETC("기타");
}
