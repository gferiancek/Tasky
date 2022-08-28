package com.gavinferiancek.tasky.agenda.domain.model

import java.time.ZonedDateTime

// Only implementing field necessary for current UI
sealed class AgendaItem {
    abstract val startTime: ZonedDateTime
}

data class Event(
    override val startTime: ZonedDateTime,
): AgendaItem()

data class Reminder(
    override val startTime: ZonedDateTime,
): AgendaItem()

data class Task(
    override val startTime: ZonedDateTime,
): AgendaItem()
