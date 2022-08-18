package com.gavinferiancek.tasky.auth.data.remote.login

import com.squareup.moshi.Json

data class LoginRequestDto(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
)
