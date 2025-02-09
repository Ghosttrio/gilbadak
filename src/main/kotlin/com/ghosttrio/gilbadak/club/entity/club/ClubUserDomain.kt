package com.ghosttrio.gilbadak.club.entity.club

class ClubUserDomain(
    val userId: Long,
    val clubId: Long,
    var state: ClubJoinState
) {
    companion object {
        fun create(userId: Long, clubId: Long): ClubUserDomain {
            return ClubUserDomain(userId, clubId, ClubJoinState.PENDING)
        }
    }

    fun approvalState() : ClubUserDomain {
        this.state = ClubJoinState.APPROVAL
        return this
    }


}