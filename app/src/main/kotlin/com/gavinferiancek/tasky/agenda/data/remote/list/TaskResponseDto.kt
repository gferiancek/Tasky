package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.data.local.database.entity.TaskEntity
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

fun TaskResponseDto.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        startTime = startTime,
        remindAt = remindAt,
        isDone = isDone,
    )
}

fun List<TaskResponseDto>.toTaskEntityList(): List<TaskEntity> {
    return map { it.toTaskEntity() }
}