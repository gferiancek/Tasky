package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
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

fun AgendaResponseDto.toAgendaItemList(): List<AgendaItem> {
    return buildList {
        addAll(events.toEventList())
        addAll(tasks.toTaskList())
        addAll(reminders.toReminderList())
    }.sortedBy { it.startTime }
}