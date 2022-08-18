package com.gavinferiancek.tasky.auth.domain.user

data class User(
    val token: String,
    val fullName: String,
)
