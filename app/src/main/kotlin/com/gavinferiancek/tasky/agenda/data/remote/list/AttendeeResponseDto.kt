package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.data.local.database.entity.AttendeeEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AttendeeResponseDto(
    @Json(name = "email")
    val email: String,
    @Json(name = "fullName")
    val fullName: String,
    @Json(name = "userId")
    val userId: String,
    @Json(name = "eventId")
    val eventId: String,
    @Json(name = "isGoing")
    val isGoing: Boolean,
    @Json(name = "remindAt")
    val remindAt: Long,
)

fun AttendeeResponseDto.toAttendeeEntity(): AttendeeEntity {
    return AttendeeEntity(
        userId = userId,
        email = email,
        fullName = fullName,
        eventId = eventId,
        isGoing = isGoing,
        remindAt = remindAt,
    )
}

fun List<AttendeeResponseDto>.toAttendeeEntityList(): List<AttendeeEntity> {
    return map { it.toAttendeeEntity() }
}