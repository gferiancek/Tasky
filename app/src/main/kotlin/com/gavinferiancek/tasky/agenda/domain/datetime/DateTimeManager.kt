package com.gavinferiancek.tasky.agenda.domain.datetime

import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.core.util.UiText
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeManager {

    fun generateDayList(
        currentDay: LocalDate,
    ): List<LocalDate> {
        return (0..5).map { currentDay.plusDays(it.toLong()) }
    }

    fun calculateNeedleIndex(
        list: List<AgendaItem>,
    ): Int {
        val currentTime = ZonedDateTime.now()
        return list.indexOfLast { it.startTime.isBefore(currentTime) }
        // return agendaItems.groupBy { it.startTime.isBefore(currentTime) }.values
    }
}