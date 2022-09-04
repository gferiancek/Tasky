package com.gavinferiancek.tasky.agenda.data.remote.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AgendaResponseDto(
    @Json(name = "events")
    val events: List<EventResponseDto>,
    @Json(name = "tasks")
    val tasks: List<TaskResponseDto>,
    @Json(name = "reminders")
    val reminders: List<ReminderResponseDto>
)
