package com.gavinferiancek.tasky.auth.domain.repository

import com.gavinferiancek.tasky.auth.domain.user.User

interface AuthRepository {

    suspend fun loginUser(
        email: String,
        password: String,
    ): User

    suspend fun registerUser(
        fullName: String,
        email: String,
        password: String,
    )
}