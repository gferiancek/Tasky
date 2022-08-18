package com.gavinferiancek.tasky.auth.data.remote.login

import com.gavinferiancek.tasky.auth.domain.user.User
import com.squareup.moshi.Json

data class LoginResponseDto(
    @Json(name = "token")
    val token: String,
    @Json(name = "userId")
    val userId: String,
    @Json(name = "fullName")
    val fullName: String,
)

fun LoginResponseDto.toUserInfo(): User {
    return User(
        token = token,
        fullName = fullName,
    )
}