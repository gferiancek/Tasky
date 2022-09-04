package com.gavinferiancek.tasky.agenda.data.local.database

import androidx.room.*
import com.gavinferiancek.tasky.agenda.data.local.database.entity.AgendaEntity
import com.gavinferiancek.tasky.agenda.data.local.database.entity.AgendaWithAttendeesAndPhotos
import com.gavinferiancek.tasky.agenda.data.local.database.entity.AttendeeEntity
import com.gavinferiancek.tasky.agenda.data.local.database.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAgendaItem(item: AgendaEntity): Long

    @Update
    suspend fun updateAgendaItem(item: AgendaEntity)

    suspend fun upsertAgendaItems(items: List<AgendaEntity>) {
        items.forEach { item ->
            if (insertAgendaItem(item) < 1) {
                updateAgendaItem(item)
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAttendee(attendee: AttendeeEntity): Long

    @Update
    suspend fun updateAttendee(attendee: AttendeeEntity)

    suspend fun upsertAttendees(attendees: List<AttendeeEntity>) {
        attendees.forEach { attendee ->
            if (insertAttendee(attendee) < 1) {
                updateAttendee(attendee)
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhoto(photo: PhotoEntity): Long

    @Update
    suspend fun updatePhoto(photo: PhotoEntity)

    suspend fun upsertPhotos(photos: List<PhotoEntity>) {
        photos.forEach { photo ->
            if (insertPhoto(photo) < 1) {
                updatePhoto(photo)
            }
        }
    }

    @Transaction
    @Query("SELECT * FROM agenda_items WHERE date = :date")
    fun getAgendaItemsForDate(date: String): Flow<List<AgendaWithAttendeesAndPhotos>>
}