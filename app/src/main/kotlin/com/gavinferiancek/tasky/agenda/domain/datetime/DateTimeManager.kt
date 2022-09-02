package com.gavinferiancek.tasky.agenda.domain.datetime

import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import java.time.*

object DateTimeManager {

    fun generateDayList(
        currentDay: LocalDate,
    ): List<LocalDate> {
        return (0..5).map { currentDay.plusDays(it.toLong()) }
    }

    fun groupAgendaItemByTime(
        list: List<AgendaItem>,
    ): Map<String, List<AgendaItem>> {
        val currentTime = ZonedDateTime.now()
        return list.groupBy {
            when {
                it.startTime.isAfter(currentTime) -> "futureItems"
                else -> "pastItems"
            }
        }
    }
}