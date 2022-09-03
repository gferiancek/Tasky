package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Task
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaskResponseDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "time")
    val startTime: Long,
    @Json(name = "remindAt")
    val remindAt: Long,
    @Json(name = "isDone")
    val isDone: Boolean
)

fun TaskResponseDto.toTask(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        startTime = DateTimeManager.millisToZonedDateTime(startTime),
        remindAt = DateTimeManager.millisToZonedDateTime(remindAt),
        isDone = isDone,
    )
}

fun List<TaskResponseDto>.toTaskList(): List<Task> {
    return map { it.toTask() }
}