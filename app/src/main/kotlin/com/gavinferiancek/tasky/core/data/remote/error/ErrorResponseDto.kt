package com.gavinferiancek.tasky.core.data.remote.error

import com.squareup.moshi.Json

data class ErrorResponseDto(
    @Json(name = "message")
    val message: String
)
