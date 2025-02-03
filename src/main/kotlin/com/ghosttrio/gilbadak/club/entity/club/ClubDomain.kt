package com.ghosttrio.gilbadak.club.entity.club


class ClubDomain(
    val name: String,
    val location: String,
    val type: ClubType,
    val description: String,
    val presidentUserId: Long
) {
    companion object {
        fun create(
            name: String,
            location: String,
            type: ClubType,
            description: String,
            presidentUserId: Long
        ): ClubDomain {
            return ClubDomain(name, location, type, description, presidentUserId)
        }
    }

}
