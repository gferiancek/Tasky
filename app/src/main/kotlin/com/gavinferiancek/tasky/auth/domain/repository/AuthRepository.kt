package com.gavinferiancek.tasky.auth.domain.repository

interface AuthRepository {

    suspend fun loginUser(
        email: String,
        password: String,
    ): Result<Unit>

    suspend fun registerUser(
        fullName: String,
        email: String,
        password: String,
    ): Result<Unit>

    suspend fun authenticateToken(): Result<Unit>

    suspend fun logoutUser()
}