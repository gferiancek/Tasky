package com.gavinferiancek.tasky.core.data.remote.error

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponseDto(
    @Json(name = "message")
    val message: String
)
