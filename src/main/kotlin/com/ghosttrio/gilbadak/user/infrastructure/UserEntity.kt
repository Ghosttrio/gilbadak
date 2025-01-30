package com.ghosttrio.gilbadak.user.infrastructure

import com.ghosttrio.gilbadak.user.domain.UserStatus
import com.ghosttrio.gilbadak.util.BaseTimeEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "tb_users")
class UserEntity(
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long?,
    val email: String,
    var nickname: String,
    @Enumerated(EnumType.STRING)
    var status: UserStatus
) : BaseTimeEntity() {


}