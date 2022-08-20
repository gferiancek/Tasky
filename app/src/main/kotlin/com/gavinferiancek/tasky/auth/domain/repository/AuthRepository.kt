package com.gavinferiancek.tasky.auth.domain.repository

import com.gavinferiancek.tasky.core.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun loginUser(
        email: String,
        password: String,
    ): Flow<DataState<Unit>>

    suspend fun registerUser(
        fullName: String,
        email: String,
        password: String,
    ): Flow<DataState<Unit>>
}