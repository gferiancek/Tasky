package com.gavinferiancek.tasky.agenda.presentation.list

import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Event
import com.gavinferiancek.tasky.agenda.domain.model.Reminder
import com.gavinferiancek.tasky.agenda.domain.model.Task
import com.gavinferiancek.tasky.core.util.UiText
import java.time.LocalDate
import java.time.ZonedDateTime

data class AgendaListState(
    val items: List<AgendaItem> = listOf(
        // Fake data
        Event(startTime = ZonedDateTime.now().minusHours(1)),
        Reminder(startTime = ZonedDateTime.now().minusMinutes(5)),
        Task(startTime = ZonedDateTime.now().plusHours(1))
    ),
    val initialDate: LocalDate = LocalDate.now(),
    val dayList: List<LocalDate> = listOf(),
    val listHeader: UiText = UiText.StringResource(id = R.string.date_today),
    val selectedDayIndex: Int = 0,
    val needleIndex: Int = 0
)