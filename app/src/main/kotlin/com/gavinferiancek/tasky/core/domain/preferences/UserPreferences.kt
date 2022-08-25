package com.gavinferiancek.tasky.core.domain.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    fun getToken(): String

    fun editToken(token: String)

    fun getName(): String

    fun editName(name: String)
}
