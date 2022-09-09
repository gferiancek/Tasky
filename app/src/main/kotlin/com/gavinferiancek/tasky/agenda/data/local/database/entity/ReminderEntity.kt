package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Event
import com.gavinferiancek.tasky.agenda.domain.model.Reminder
import org.jetbrains.annotations.NotNull

@Entity(tableName = "reminders")
data class ReminderEntity(
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
)

fun ReminderEntity.toReminder(): Reminder {
    return Reminder(
        id = id,
        title = title,
        description = description,
        startTime = DateTimeManager.millisToZonedDateTime(startTime),
        remindAt = DateTimeManager.millisToZonedDateTime(remindAt),
    )
}

fun List<ReminderEntity>.toReminderList(): List<Reminder> {
    return map { it.toReminder() }
}
