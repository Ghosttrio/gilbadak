//package com.ghosttrio.gilbadak.user.domain
//
//class UserDomain(
//
//
//) {
//    //    constructor() : this(null, "", "") JPA Gradle 플러그인으로 제거
//    constructor(email: String, name: String) : this(null, Profile(email, name), UserStatus.ACTIVATED)
//
//    fun changeNickname(nickname: String) {
//        profile.changeNickname(nickname)
//    }
//    fun withdraw() {
//        this.status = UserStatus.DELETED
//    }
//}