package com.gavinferiancek.tasky.auth.data.remote.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestDto(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
)
