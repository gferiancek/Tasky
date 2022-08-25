package com.gavinferiancek.tasky.agenda.di

import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgendaModule {

    @Singleton
    @Provides
    fun provideDateTimeManager(): DateTimeManager {
        return DateTimeManager()
    }
}