package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Attendee
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

fun AttendeeResponseDto.toAttendee(): Attendee {
    return Attendee(
        email = email,
        fullName = fullName,
        userId = userId,
        eventId = eventId,
        isGoing = isGoing,
        remindAt = DateTimeManager.millisToZonedDateTime(remindAt),
    )
}

fun List<AttendeeResponseDto>.toAttendeeList(): List<Attendee> {
    return map { it.toAttendee() }
}