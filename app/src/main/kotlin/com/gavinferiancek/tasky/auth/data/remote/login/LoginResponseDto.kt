package com.gavinferiancek.tasky.auth.data.remote.login

import com.squareup.moshi.Json

data class LoginResponseDto(
    @Json(name = "token")
    val token: String,
    @Json(name = "userId")
    val userId: String,
    @Json(name = "fullName")
    val fullName: String,
)