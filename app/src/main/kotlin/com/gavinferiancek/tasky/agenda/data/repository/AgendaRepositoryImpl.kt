package com.gavinferiancek.tasky.agenda.data.repository

import com.gavinferiancek.tasky.agenda.data.local.database.AgendaDao
import com.gavinferiancek.tasky.agenda.data.local.database.entity.toAgendaItemList
import com.gavinferiancek.tasky.agenda.data.remote.AgendaApi
import com.gavinferiancek.tasky.agenda.data.remote.list.toAgendaEntityList
import com.gavinferiancek.tasky.agenda.data.remote.list.toAttendeeEntityList
import com.gavinferiancek.tasky.agenda.data.remote.list.toPhotoEntityList
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.time.ZoneOffset


class AgendaRepositoryImpl(
    private val api: AgendaApi,
    private val dao: AgendaDao,
) : AgendaRepository {

    override fun getCachedAgendaForDate(date: String): Flow<List<AgendaItem>> {
        return dao.getAgendaItemsForDate(date).map { it.toAgendaItemList() }
    }

    override suspend fun fetchAgendaForDate(timestamp: Long): Result<Unit> {
        return try {
            val items = api.getAgenda(
                timeZone = ZoneOffset.systemDefault().id,
                timestamp = timestamp,
            )
            dao.upsertAgendaItems(items.toAgendaEntityList())
            items.events.forEach { event ->
                dao.upsertAttendees(event.attendees.toAttendeeEntityList())
                dao.upsertPhotos(event.photos.toPhotoEntityList(event.id))
            }
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        }
    }
}