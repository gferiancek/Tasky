package com.gavinferiancek.tasky.agenda.domain.localdate

import java.time.LocalDate

class LocalDateManager {

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
}