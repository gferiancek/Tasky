package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey @ColumnInfo(name = "event_id")
    val eventId: String,
    @NotNull @ColumnInfo(name = "date")
    val date: String,
    @NotNull @ColumnInfo(name = "title")
    val title: String,
    @NotNull @ColumnInfo(name = "description")
    val description: String,
    @NotNull @ColumnInfo(name = "start_time")
    val startTime: Long,
    @NotNull @ColumnInfo(name = "remind_at")
    val remindAt: Long,
    @NotNull @ColumnInfo(name = "end_time")
    val endTime: Long,
    @NotNull @ColumnInfo(name = "host_id")
    val hostId: String,
    @NotNull @ColumnInfo(name = "is_creator")
    val isCreator: Boolean,
)