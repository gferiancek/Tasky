package com.gavinferiancek.tasky.core.di

import android.content.Context
import com.gavinferiancek.tasky.BuildConfig
import com.gavinferiancek.tasky.core.data.local.datastore.UserStoreImpl
import com.gavinferiancek.tasky.core.domain.datastore.UserStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext appContext: Context): UserStore {
        return UserStoreImpl(context = appContext)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(userStore: UserStore): OkHttpClient {
        var token = ""
        CoroutineScope(Dispatchers.IO).launch {
            token = userStore.getToken().first()
        }
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", BuildConfig.API_KEY)
                    .addHeader("Authorization", "Bearer $token")
                chain.proceed(request.build())
            }
            .build()
    }
}