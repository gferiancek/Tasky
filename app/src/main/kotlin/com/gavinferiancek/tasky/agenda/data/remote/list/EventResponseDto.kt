package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Event
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventResponseDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "from")
    val startTime: Long,
    @Json(name = "remindAt")
    val remindAt: Long,
    @Json(name = "to")
    val endTime: Long,
    @Json(name = "host")
    val hostId: String,
    @Json(name = "isUserEventCreator")
    val isCreator: Boolean,
    @Json(name = "attendees")
    val attendees: List<AttendeeResponseDto>,
    @Json(name = "photos")
    val photos: List<PhotoResponseDto>
)

fun EventResponseDto.toEvent(): Event {
    return Event(
        id = id,
        title = title,
        description = description,
        startTime = DateTimeManager.millisToZonedDateTime(startTime),
        remindAt = DateTimeManager.millisToZonedDateTime(remindAt),
        endTime = DateTimeManager.millisToZonedDateTime(endTime),
        hostId = hostId,
        isCreator = isCreator,
        attendees = attendees.toAttendeeList(),
        photos = photos.toPhotoList(),
    )
}

fun List<EventResponseDto>.toEventList(): List<Event> {
    return map { it.toEvent() }
}