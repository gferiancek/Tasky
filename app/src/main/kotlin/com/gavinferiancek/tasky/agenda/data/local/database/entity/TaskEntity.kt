package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Reminder
import com.gavinferiancek.tasky.agenda.domain.model.Task
import org.jetbrains.annotations.NotNull

@Entity(tableName = "tasks")
data class TaskEntity(
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
    @NotNull @ColumnInfo(name = "is_done")
    val isDone: Boolean,
)

fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        startTime = DateTimeManager.millisToZonedDateTime(startTime),
        remindAt = DateTimeManager.millisToZonedDateTime(remindAt),
        isDone = isDone,
    )
}

fun List<TaskEntity>.toTaskList(): List<Task> {
    return map { it.toTask() }
}
