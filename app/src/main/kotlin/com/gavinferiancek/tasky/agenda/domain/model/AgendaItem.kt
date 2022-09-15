package com.gavinferiancek.tasky.agenda.domain.model

import com.gavinferiancek.tasky.agenda.data.local.database.entity.TaskEntity
import com.gavinferiancek.tasky.agenda.data.remote.shared.TaskRequestDto
import java.time.ZonedDateTime

sealed class AgendaItem {
    abstract val id: String
    abstract val title: String
    abstract val description: String
    abstract val startTime: ZonedDateTime
    abstract val remindAt: ZonedDateTime
}

data class Event(
    override val id: String,
    override val title: String,
    override val description: String,
    override val startTime: ZonedDateTime,
    override val remindAt: ZonedDateTime,
    val endTime: ZonedDateTime,
    val hostId: String,
    val isCreator: Boolean,
    val attendees: List<Attendee>,
    val photos: List<Photo>
) : AgendaItem()

data class Task(
    override val id: String,
    override val title: String,
    override val description: String,
    override val startTime: ZonedDateTime,
    override val remindAt: ZonedDateTime,
    val isDone: Boolean
) : AgendaItem()

fun Task.toTaskRequestDto(): TaskRequestDto {
    return TaskRequestDto(
        id = id,
        title = title,
        description = description,
        startTime = startTime.toInstant().toEpochMilli(),
        remindAt = remindAt.toInstant().toEpochMilli(),
        isDone = isDone,
    )
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        startTime = startTime.toInstant().toEpochMilli(),
        remindAt = remindAt.toInstant().toEpochMilli(),
        isDone = isDone,
    )
}

data class Reminder(
    override val id: String,
    override val title: String,
    override val description: String,
    override val startTime: ZonedDateTime,
    override val remindAt: ZonedDateTime,
) : AgendaItem()
