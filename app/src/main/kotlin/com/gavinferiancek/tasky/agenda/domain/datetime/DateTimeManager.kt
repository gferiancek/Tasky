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
     * Converts a Unix Timestamp into a string date in the format of "YYYY-MM-DD".  Used when converting
     * ResponseDtos to Entity classes allowing us to easily query the DB based on this date string. (UI
     * uses LocalDate already, so DB queries by date are simple.)
     */
    fun millisToDateString(
        millis: Long
    ): String {
        return millisToZonedDateTime(millis).toLocalDate().toString()
    }

    /**
     * AgendaHeader.kt contains a library that uses LocalDate for a DatePicker dialog, so all of the UI involving
     * selecting the day to display uses LocalDate to conform to that standard.  However, when communicating with
     * the API we need to provide a Unix timestamp. We first convert to ZonedDateTime to make sure our timestamp will
     * be for the intended day. (API takes timestamp and timezone id to calculate the returned Agenda's day.)
     */
    fun localDateToMillis(
        date: LocalDate
    ): Long {
        return ZonedDateTime.of(
            date,
            LocalTime.now(),
            ZoneOffset.systemDefault()
        ).toInstant().toEpochMilli()
    }
}