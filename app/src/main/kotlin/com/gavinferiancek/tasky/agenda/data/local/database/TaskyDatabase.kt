package com.gavinferiancek.tasky.agenda.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gavinferiancek.tasky.agenda.data.local.database.entity.*

@Database(
    entities = [
        AttendeeEntity::class,
        EventEntity::class,
        PhotoEntity::class,
        ReminderEntity::class,
        TaskEntity::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class TaskyDatabase : RoomDatabase() {
    abstract val dao: AgendaDao
}