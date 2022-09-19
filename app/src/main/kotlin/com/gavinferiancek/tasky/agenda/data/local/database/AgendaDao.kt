package com.gavinferiancek.tasky.agenda.data.local.database

import androidx.room.*
import com.gavinferiancek.tasky.agenda.data.local.database.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaDao {

    /**
     * TASKS
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTask(id: String)

    @Query("SELECT * FROM tasks WHERE start_time BETWEEN :startTime AND :endTime")
    fun getTasksForDate(startTime: Long, endTime: Long): Flow<List<TaskEntity>>

    /**
     * REMINDERS
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminders(reminders: List<ReminderEntity>)

    @Query("DELETE FROM reminders WHERE id = :id")
    suspend fun deleteReminder(id: String)

    @Query("SELECT * FROM reminders WHERE start_time BETWEEN :startTime AND :endTime")
    fun getRemindersForDate(startTime: Long, endTime: Long): Flow<List<ReminderEntity>>

    /**
     * EVENTS
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: EventEntity): Long

    @Update
    suspend fun updateEvent(event: EventEntity)

    suspend fun upsertEvents(events: List<EventEntity>) {
        events.forEach { event ->
            if (insertEvent(event) < 1) {
                updateEvent(event)
            }
        }
    }

    @Query("DELETE FROM events WHERE id = :id")
    suspend fun deleteEvent(id: String)

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

    @Query("SELECT * FROM events WHERE start_time BETWEEN :startTime AND :endTime")
    fun getEventsForDate(startTime: Long, endTime: Long): Flow<List<EventEntity>>
}