package com.gavinferiancek.tasky.agenda.data.repository

import com.gavinferiancek.tasky.agenda.data.local.database.AgendaDao
import com.gavinferiancek.tasky.agenda.data.local.database.entity.toEventList
import com.gavinferiancek.tasky.agenda.data.local.database.entity.toReminderList
import com.gavinferiancek.tasky.agenda.data.local.database.entity.toTaskList
import com.gavinferiancek.tasky.agenda.data.remote.AgendaApi
import com.gavinferiancek.tasky.agenda.data.remote.list.*
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import retrofit2.HttpException
import java.time.LocalDate
import java.time.ZoneOffset


class AgendaRepositoryImpl(
    private val api: AgendaApi,
    private val dao: AgendaDao,
    private val dateManager: DateTimeManager,
) : AgendaRepository {

    override fun getCachedAgendaForDate(date: LocalDate): Flow<List<AgendaItem>> {
        val startTime = dateManager.localDateToMillis(
            date = date,
            time = date.atStartOfDay().toLocalTime(),
        )
        val endTime = dateManager.localDateToMillis(
            date = date.plusDays(1),
            time = date.plusDays(1).atStartOfDay().toLocalTime(),
        ) - 1

        return combine(
            dao.getEventsForDate(startTime = startTime, endTime = endTime),
            dao.getTasksForDate(startTime = startTime, endTime = endTime),
            dao.getRemindersForDate(startTime = startTime, endTime = endTime)
        ) { events, tasks, reminders ->
            buildList {
                addAll(events.toEventList())
                addAll(tasks.toTaskList())
                addAll(reminders.toReminderList())
            }.sortedBy { it.startTime }
        }.distinctUntilChanged()
    }

    override suspend fun fetchAgendaForDate(timestamp: Long): Result<Unit> {
        return try {
            val items = api.getAgenda(
                timeZone = ZoneOffset.systemDefault().id,
                timestamp = timestamp,
            )
            dao.upsertEvents(events = items.events.toEventEntityList())
            items.events.forEach { event ->
                dao.upsertAttendees(attendees = event.attendees.toAttendeeEntityList())
                dao.upsertPhotos(photos = event.photos.toPhotoEntityList(event.id))
            }
            dao.insertTasks(tasks = items.tasks.toTaskEntityList())
            dao.insertReminders(reminders = items.reminders.toReminderEntityList())
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}