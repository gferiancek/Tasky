package com.gavinferiancek.tasky.core.domain.datastore

import kotlinx.coroutines.flow.Flow

interface UserStore {

    fun getToken(): Flow<String>

    suspend fun editToken(token: String)

    fun getName(): Flow<String>

    suspend fun editName(name: String)
}