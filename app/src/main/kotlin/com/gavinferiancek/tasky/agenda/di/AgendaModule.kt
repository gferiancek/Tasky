package com.gavinferiancek.tasky.agenda.di

import android.content.Context
import androidx.room.Room
import com.gavinferiancek.tasky.BuildConfig
import com.gavinferiancek.tasky.agenda.data.local.database.TaskyDatabase
import com.gavinferiancek.tasky.agenda.data.remote.AgendaApi
import com.gavinferiancek.tasky.agenda.data.repository.AgendaRepositoryImpl
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.repository.AgendaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgendaModule {

    @Singleton
    @Provides
    fun provideDateTimeManager(): DateTimeManager {
        return DateTimeManager
    }

    @Singleton
    @Provides
    fun provideAgendaApi(client: OkHttpClient): AgendaApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideTaskyDatabase(@ApplicationContext appContext: Context): TaskyDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskyDatabase::class.java,
            "tasky_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAgendaRepository(
        api: AgendaApi,
        database: TaskyDatabase,
    ): AgendaRepository {
        return AgendaRepositoryImpl(
            api = api,
            dao = database.dao,
        )
    }
}