package com.gavinferiancek.tasky.auth.di

import com.gavinferiancek.tasky.BuildConfig
import com.gavinferiancek.tasky.auth.data.local.validation.EmailMatcherImpl
import com.gavinferiancek.tasky.auth.data.remote.AuthApi
import com.gavinferiancek.tasky.auth.data.repository.AuthRepositoryImpl
import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository
import com.gavinferiancek.tasky.auth.domain.validation.EmailMatcher
import com.gavinferiancek.tasky.auth.domain.validation.TextValidationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideTextValidationManager(emailMatcher: EmailMatcher): TextValidationManager {
        return TextValidationManager(emailMatcher)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", BuildConfig.API_KEY)
                chain.proceed(request.build())
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
    ): AuthRepository {
        return AuthRepositoryImpl(authApi = authApi)
    }
}