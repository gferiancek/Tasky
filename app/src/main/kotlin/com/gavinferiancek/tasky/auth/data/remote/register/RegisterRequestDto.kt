package com.gavinferiancek.tasky.auth.data.remote.register

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequestDto(
    @Json(name = "fullName")
    val fullName: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
)
