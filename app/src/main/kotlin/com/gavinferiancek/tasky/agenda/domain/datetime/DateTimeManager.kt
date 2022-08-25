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

    fun generateListHeader(
        selectedDay: LocalDate,
    ): UiText {
        val currentDay = LocalDate.now()
        return when (selectedDay) {
            currentDay.minusDays(1) -> UiText.StringResource(id = R.string.date_yesterday)
            currentDay -> UiText.StringResource(id = R.string.date_today)
            currentDay.plusDays(1) -> UiText.StringResource(id = R.string.date_tomorrow)
            else -> {
                val formatter = DateTimeFormatter.ofPattern("E d, u")
                UiText.DynamicString(selectedDay.format(formatter))
            }
        }
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