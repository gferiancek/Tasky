package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Event
import org.jetbrains.annotations.NotNull

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: String,
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

fun EventEntity.toEvent(): Event {
    return Event(
        id = id,
        title = title,
        description = description,
        startTime = DateTimeManager.millisToZonedDateTime(startTime),
        remindAt = DateTimeManager.millisToZonedDateTime(remindAt),
        endTime = DateTimeManager.millisToZonedDateTime(endTime),
        hostId = hostId,
        isCreator = isCreator,
        attendees = listOf(),
        photos = listOf(),
    )
}

fun List<EventEntity>.toEventList(): List<Event> {
    return map { it.toEvent() }
}
