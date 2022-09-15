package com.gavinferiancek.tasky.agenda.domain.repository

import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AgendaRepository {

    fun getCachedAgendaForDate(date: LocalDate): Flow<List<AgendaItem>>

    suspend fun fetchAgendaForDate(timestamp: Long): Result<Unit>

    suspend fun deleteEvent(id: String): Result<Unit>

    suspend fun updateTask(task: Task): Result<Unit>

    suspend fun deleteTask(id: String): Result<Unit>

    suspend fun deleteReminder(id: String): Result<Unit>
}