package com.gavinferiancek.tasky.core.data.remote.interceptor

import com.gavinferiancek.tasky.BuildConfig
import com.gavinferiancek.tasky.core.domain.preferences.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val userPreferences: UserPreferences): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = userPreferences.getUser().token
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", BuildConfig.API_KEY)
            .addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}