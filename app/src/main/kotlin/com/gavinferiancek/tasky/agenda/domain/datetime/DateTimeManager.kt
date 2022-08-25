package com.gavinferiancek.tasky.agenda.domain.datetime

import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.core.util.UiText
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DateTimeManager {

    fun generateDayList(
        currentDay: LocalDate,
    ): List<LocalDate> {
        val listSize = 6
        val days = mutableListOf<LocalDate>()
        for (i in 0 until listSize) {
            days.add(currentDay.plusDays(i.toLong()))
        }
        return days
    }

    fun calculateNeedleIndex(
        list: List<AgendaItem>,
    ): Int {
        val currentTime = ZonedDateTime.now()
        return list.indexOfLast { it.startTime.isBefore(currentTime) }
    }

    companion object {
        // Fun was needed in static setting to apply the date to each AgendaItem in LazyColumn.
        // Didn't want to make a separate Util class since it fits in with this one, so I think this
        // is the appropriate best practice.
        fun formatZonedDate(
            zonedDateTime: ZonedDateTime
        ): String {
            val formatter = DateTimeFormatter.ofPattern("MMM, HH:mm")
            return zonedDateTime.format(formatter)
        }
    }
}