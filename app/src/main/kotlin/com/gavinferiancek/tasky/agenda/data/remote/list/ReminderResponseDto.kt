package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Reminder
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReminderResponseDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "time")
    val startTime: Long,
    @Json(name = "remindAt")
    val remindAt: Long,
)

fun ReminderResponseDto.toReminder(): Reminder {
    return Reminder(
        id = id,
        title = title,
        description = description,
        startTime = DateTimeManager.millisToZonedDateTime(startTime),
        remindAt = DateTimeManager.millisToZonedDateTime(remindAt),
    )
}

fun List<ReminderResponseDto>.toReminderList(): List<Reminder> {
    return map { it.toReminder() }
}