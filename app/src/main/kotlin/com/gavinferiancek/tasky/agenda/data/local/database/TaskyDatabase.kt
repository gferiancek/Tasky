package com.gavinferiancek.tasky.agenda.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gavinferiancek.tasky.agenda.data.local.database.entity.AgendaEntity
import com.gavinferiancek.tasky.agenda.data.local.database.entity.AttendeeEntity
import com.gavinferiancek.tasky.agenda.data.local.database.entity.PhotoEntity

@Database(
    entities = [
        AgendaEntity::class,
        AttendeeEntity::class,
        PhotoEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class TaskyDatabase : RoomDatabase() {
    abstract val dao: AgendaDao
}