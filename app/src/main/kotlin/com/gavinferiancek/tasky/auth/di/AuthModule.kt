package com.gavinferiancek.tasky.auth.di

import com.gavinferiancek.tasky.auth.data.validation.EmailMatcherImpl
import com.gavinferiancek.tasky.auth.domain.validation.EmailMatcher
import com.gavinferiancek.tasky.auth.domain.validation.TextValidationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @ViewModelScoped
    fun provideTextValidationManager(emailMatcher: EmailMatcher): TextValidationManager {
        return TextValidationManager(emailMatcher)
    }
}