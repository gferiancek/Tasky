package com.gavinferiancek.tasky.core.domain.preferences

import com.gavinferiancek.tasky.core.domain.User
import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    fun editToken(token: String)

    fun editName(name: String)

    fun editId(id: String)

    fun getUser(): User
}
