package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.data.local.database.entity.EventEntity
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

fun EventResponseDto.toEventEntity(): EventEntity {
    return EventEntity(
        id = id,
        title = title,
        description = description,
        startTime = startTime,
        remindAt = remindAt,
        endTime = endTime,
        hostId = hostId,
        isCreator = isCreator,
    )
}

fun List<EventResponseDto>.toEventEntityList(): List<EventEntity> {
    return map { it.toEventEntity() }
}