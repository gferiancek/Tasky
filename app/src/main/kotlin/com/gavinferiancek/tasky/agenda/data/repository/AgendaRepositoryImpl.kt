package com.gavinferiancek.tasky.agenda.data.repository

import com.gavinferiancek.tasky.agenda.data.local.database.AgendaDao
import com.gavinferiancek.tasky.agenda.data.local.database.entity.toEventList
import com.gavinferiancek.tasky.agenda.data.local.database.entity.toReminderList
import com.gavinferiancek.tasky.agenda.data.local.database.entity.toTaskList
import com.gavinferiancek.tasky.agenda.data.remote.AgendaApi
import com.gavinferiancek.tasky.agenda.data.remote.list.*
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Task
import com.gavinferiancek.tasky.agenda.domain.model.toTaskEntity
import com.gavinferiancek.tasky.agenda.domain.model.toTaskRequestDto
import com.gavinferiancek.tasky.agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
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
            supervisorScope {
                launch {
                    dao.upsertEvents(events = items.events.toEventEntityList())
                    items.events.forEach { event ->
                        launch { dao.upsertAttendees(attendees = event.attendees.toAttendeeEntityList()) }
                        launch { dao.upsertPhotos(photos = event.photos.toPhotoEntityList(event.id)) }
                    }
                }
                launch { dao.insertTasks(tasks = items.tasks.toTaskEntityList()) }
                launch { dao.insertReminders(reminders = items.reminders.toReminderEntityList()) }
            }
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }

    override suspend fun updateTask(task: Task): Result<Unit> {
        return try {
            // Not 100% sure how I will track local pending changes so I'm calling insert first
            // to ensure it is called. If I go with the hasPendingChanges method (noted in catch block),
            // then this insert will happen after the API call so there's only a single insert.
            // (Either with hasPendingChanges false or in the case of an exception, true)
            dao.insertTasks(listOf(task.toTaskEntity()))
            api.updateTask(task.toTaskRequestDto())
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            // TODO Save Task to separate table specifically for items with pending changes OR
            //  add hasPendingChanges field to Entity Class and insert into DB
            Result.failure(e)
        }
    }

    override suspend fun deleteEvent(id: String): Result<Unit> {
        return try {
            dao.deleteEvent(id = id)
            api.deleteEvent(id = id)
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            // TODO Save id to db use with syncAgenda endpoint.
            Result.failure(e)
        }
    }

    override suspend fun deleteTask(id: String): Result<Unit> {
        return try {
            dao.deleteTask(id = id)
            api.deleteTask(id = id)
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            // TODO Save id to db use with syncAgenda endpoint.
            Result.failure(e)
        }
    }

    override suspend fun deleteReminder(id: String): Result<Unit> {
        return try {
            dao.deleteReminder(id = id)
            api.deleteReminder(id = id)
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            // TODO Save id to db use with syncAgenda endpoint.
            Result.failure(e)
        }
    }
}