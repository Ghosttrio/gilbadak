package com.ghosttrio.gilbadak.club.entity.club


class ClubDomain(
    var name: String,
    val location: String,
    val type: ClubType,
    var description: String,
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

    fun updateClubInformation(name: String, description: String) : ClubDomain{
        this.name = name
        this.description = description
        return this
    }
}
