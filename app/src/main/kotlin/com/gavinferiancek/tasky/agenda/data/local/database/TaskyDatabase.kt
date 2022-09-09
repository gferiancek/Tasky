package com.gavinferiancek.tasky.agenda.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gavinferiancek.tasky.agenda.data.local.database.entity.*

@Database(
    entities = [
        EventEntity::class,
        TaskEntity::class,
        ReminderEntity::class,
        AttendeeEntity::class,
        PhotoEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class TaskyDatabase : RoomDatabase() {
    abstract val dao: AgendaDao
}