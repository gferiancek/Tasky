package com.gavinferiancek.tasky.core.di

import android.content.Context
import com.gavinferiancek.tasky.core.data.local.preferences.UserPreferencesImpl
import com.gavinferiancek.tasky.core.data.remote.interceptor.AuthorizationInterceptor
import com.gavinferiancek.tasky.core.domain.preferences.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext appContext: Context): UserPreferences {
        return UserPreferencesImpl(context = appContext)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(userPreferences: UserPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(AuthorizationInterceptor(userPreferences))
            .build()
    }
}