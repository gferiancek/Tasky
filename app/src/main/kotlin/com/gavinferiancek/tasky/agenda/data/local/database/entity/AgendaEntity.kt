package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "agenda_items")
data class AgendaEntity(
    // Shared Columns
    @PrimaryKey @ColumnInfo(name = "id")
    val id: String,
    @NotNull @ColumnInfo(name = "type")
    val type: Type,
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

    // Event Columns
    @NotNull @ColumnInfo(name = "end_time")
    val endTime: Long? = null,
    @NotNull @ColumnInfo(name = "host_id")
    val hostId: String? = null,
    @NotNull @ColumnInfo(name = "is_creator")
    val isCreator: Boolean? = null,

    // Task Columns
    @NotNull @ColumnInfo(name = "is_done")
    val isDone: Boolean? = null
) {
    enum class Type {
        EVENT,
        TASK,
        REMINDER,
    }
}