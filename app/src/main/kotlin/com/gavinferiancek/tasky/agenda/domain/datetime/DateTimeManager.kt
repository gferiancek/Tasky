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

    fun millisToZonedDateTime(
        millis: Long,
    ): ZonedDateTime {
        return ZonedDateTime.ofInstant(
            Instant.ofEpochMilli(millis),
            ZoneOffset.systemDefault(),
        )
    }

    /**
     * AgendaHeader.kt contains a library that uses LocalDate for a DatePicker dialog, so all of the UI involving
     * selecting the day to display uses LocalDate to conform to that standard.  However, when communicating with
     * the API we need to provide a Unix timestamp. We first convert to ZonedDateTime to make sure our timestamp will
     * be for the intended day. (API takes timestamp and timezone id to calculate the returned Agenda's day.)
     */
    fun localDateToMillis(
        date: LocalDate,
        time: LocalTime = LocalTime.now(),
    ): Long {
        return ZonedDateTime.of(
            date,
            time,
            ZoneOffset.UTC
        ).toInstant().toEpochMilli()
    }
}