package com.ghosttrio.gilbadak.club.entity.club

class ClubUserDomain(
    val userId: Long,
    val clubId: Long,
    val state: ClubJoinState
) {
    companion object {
        fun create(userId: Long, clubId: Long): ClubUserDomain {
            return ClubUserDomain(userId, clubId, ClubJoinState.PENDING)
        }
    }
}