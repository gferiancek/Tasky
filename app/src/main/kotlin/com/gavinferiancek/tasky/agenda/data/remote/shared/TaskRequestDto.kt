package com.gavinferiancek.tasky.agenda.data.remote.shared

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaskRequestDto(
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
    val isDone: Boolean,
)