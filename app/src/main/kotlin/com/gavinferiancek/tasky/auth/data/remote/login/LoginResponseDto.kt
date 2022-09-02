package com.gavinferiancek.tasky.auth.data.remote.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseDto(
    @Json(name = "token")
    val token: String,
    @Json(name = "userId")
    val userId: String,
    @Json(name = "fullName")
    val fullName: String,
)