package com.gavinferiancek.tasky.agenda.data.repository

import com.gavinferiancek.tasky.agenda.data.remote.AgendaApi
import com.gavinferiancek.tasky.agenda.data.remote.list.toAgendaItemList
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.repository.AgendaRepository
import java.util.*
import java.util.concurrent.CancellationException


class AgendaRepositoryImpl(
    private val api: AgendaApi,
): AgendaRepository {
    override suspend fun getAgenda(
        time: Long,
    ): Result<List<AgendaItem>> {
        return try {
            val items = api.getAgenda(
                timeZone = TimeZone.getDefault().id,
                time = time
            ).toAgendaItemList()
            Result.success(items)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Result.failure(e)
        }
    }

    override suspend fun deleteEvent() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteReminder() {
        TODO("Not yet implemented")
    }
}