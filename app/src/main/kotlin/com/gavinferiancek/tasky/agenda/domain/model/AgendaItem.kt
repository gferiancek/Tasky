package com.gavinferiancek.tasky.agenda.domain.model

import java.time.ZonedDateTime

// Only implementing field necessary for current UI
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
): AgendaItem()

data class Task(
    override val id: String,
    override val title: String,
    override val description: String,
    override val startTime: ZonedDateTime,
    override val remindAt: ZonedDateTime,
    val isDone: Boolean
): AgendaItem()

data class Reminder(
    override val id: String,
    override val title: String,
    override val description: String,
    override val startTime: ZonedDateTime,
    override val remindAt: ZonedDateTime,
): AgendaItem()
