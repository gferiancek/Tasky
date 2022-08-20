package com.gavinferiancek.tasky.core.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.gavinferiancek.tasky.core.domain.datastore.UserStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserStoreImpl (
    private val context: Context,
): UserStore {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    private object PreferencesKeys {
        val TOKEN_KEY = stringPreferencesKey(name = "token")
        val NAME_KEY = stringPreferencesKey(name = "name")
    }

    override fun getToken() = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.TOKEN_KEY]?: "" }

    override suspend fun editToken(token: String) {
        context.dataStore.edit {
            it[PreferencesKeys.TOKEN_KEY] = token
        }
    }

    override fun getName() = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.NAME_KEY]?: "" }

    override suspend fun editName(name: String) {
        context.dataStore.edit {
            it[PreferencesKeys.NAME_KEY] = name
        }
    }
}