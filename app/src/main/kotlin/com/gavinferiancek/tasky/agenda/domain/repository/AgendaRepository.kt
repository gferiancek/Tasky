package com.gavinferiancek.tasky.agenda.domain.repository

import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem

interface AgendaRepository {

    // TODO Change to flow once database is implemented
    suspend fun getAgenda(
        time: Long,
    ): Result<List<AgendaItem>>

    suspend fun deleteEvent()

    suspend fun deleteTask()

    suspend fun deleteReminder()
}