package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.data.local.database.entity.AgendaEntity
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
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

fun ReminderResponseDto.toAgendaEntity(): AgendaEntity {
    return AgendaEntity(
        id = id,
        type = AgendaEntity.Type.REMINDER,
        date = DateTimeManager.millisToDateString(startTime),
        title = title,
        description = description,
        startTime = startTime,
        remindAt = remindAt,
    )
}

fun List<ReminderResponseDto>.toAgendaEntityList(): List<AgendaEntity> {
    return map { it.toAgendaEntity() }
}