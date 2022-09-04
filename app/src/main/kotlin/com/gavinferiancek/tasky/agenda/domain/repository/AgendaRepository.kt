package com.gavinferiancek.tasky.agenda.domain.repository

import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {

    fun getCachedAgendaForDate(date: String): Flow<List<AgendaItem>>

    suspend fun fetchAgendaForDate(timestamp: Long): Result<Unit>
}