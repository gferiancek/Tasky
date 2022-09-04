package com.gavinferiancek.tasky.agenda.data.local.database

import androidx.room.*
import com.gavinferiancek.tasky.agenda.data.local.database.entity.EventEntity
import com.gavinferiancek.tasky.agenda.data.local.database.entity.EventWithAttendeesAndPhotos
import com.gavinferiancek.tasky.agenda.data.local.database.entity.ReminderEntity
import com.gavinferiancek.tasky.agenda.data.local.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaDao {

    // EVENTS
    @Insert
    suspend fun insertEvent(event: EventEntity): Long

    @Update
    suspend fun updateEvent(event: EventEntity)

    /**
     * Since Attendee/Photo Entities have ForeignKeys with Events we want to avoid using
     * OnConflictStrategy.REPLACE, as that would delete and recreate child entities every time an Event
     * is updated. Instead, we want to use Update for existing records.
     */
    suspend fun upsertEvents(events: List<EventEntity>) {
        events.forEach { event ->
            if (insertEvent(event) < 1) {
                updateEvent(event)
            }
        }
    }

    @Transaction
    @Query("SELECT * FROM events WHERE date = :date")
    fun getEventsForDate(date: String): Flow<List<EventWithAttendeesAndPhotos>>


    // TASKS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Query("SELECT * FROM tasks WHERE date = :date")
    fun getTasksForDate(date: String): Flow<List<TaskEntity>>


    // REMINDERS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminders(reminders: List<ReminderEntity>)

    @Query("SELECT * FROM reminders WHERE date = :date")
    fun getRemindersForDate(date: String): Flow<List<ReminderEntity>>
}