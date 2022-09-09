package com.gavinferiancek.tasky.agenda.domain.repository

import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface AgendaRepository {

    fun getCachedAgendaForDate(date: LocalDate): Flow<List<AgendaItem>>

    suspend fun fetchAgendaForDate(timestamp: Long): Result<Unit>
}