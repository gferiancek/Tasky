package com.gavinferiancek.tasky.core.data.local.preferences

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.gavinferiancek.tasky.core.domain.User
import com.gavinferiancek.tasky.core.domain.preferences.UserPreferences

class UserPreferencesImpl(
    context: Context,
): UserPreferences {
    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val userPreferences = EncryptedSharedPreferences.create(
        context,
        "user_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun editToken(token: String) {
        userPreferences
            .edit()
            .putString("token", token)
            .apply()
    }

    override fun editName(name: String) {
        userPreferences
            .edit()
            .putString("name", name)
            .apply()
    }

    override fun editId(id: String) {
        userPreferences
            .edit()
            .putString("id", id)
            .apply()
    }

    override fun getUser(): User {
        return User(
            token = userPreferences.getString("token", null)?: "",
            name = userPreferences.getString("name", null)?: "",
            id = userPreferences.getString("id", null)?: "",
        )
    }
}