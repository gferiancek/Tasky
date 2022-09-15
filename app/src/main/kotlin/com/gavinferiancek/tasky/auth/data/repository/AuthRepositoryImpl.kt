package com.gavinferiancek.tasky.auth.data.repository

import com.gavinferiancek.tasky.auth.data.remote.AuthApi
import com.gavinferiancek.tasky.auth.data.remote.login.LoginRequestDto
import com.gavinferiancek.tasky.auth.data.remote.register.RegisterRequestDto
import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository
import com.gavinferiancek.tasky.core.domain.preferences.UserPreferences
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.CancellationException

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val userPreferences: UserPreferences,
) : AuthRepository {

    override suspend fun loginUser(
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            val authorizedUser = authApi.loginUser(
                loginRequest = LoginRequestDto(
                    email = email,
                    password = password,
                )
            )
            userPreferences.editToken(token = authorizedUser.token)
            userPreferences.editName(name = authorizedUser.fullName)
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            else Result.failure(e)
        }
    }


    override suspend fun registerUser(
        fullName: String,
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            authApi.registerUser(
                registerRequest = RegisterRequestDto(
                    fullName = fullName,
                    email = email,
                    password = password,
                )
            )
            Result.success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            else Result.failure(e)
        }
    }

    override suspend fun authenticateToken(): Result<Unit> {
        return try {
            authApi.authenticateToken()
            Result.success(Unit)

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            else {
                if (e is HttpException && e.code() == 401) userPreferences.editToken("")
                Result.failure(e)
            }
        }
    }

    override suspend fun logoutUser() {
        try {
            authApi.logoutUser()
        }
        // Nothing to do with these exceptions, but they're caught to prevent the app from crashing.
        // (Imagine hitting logout in airplane mode and having the app crash instead of logging out.)
        catch (e: HttpException) {
        } catch (e: IOException) {
        } finally {
            userPreferences.editName("")
            userPreferences.editToken("")
        }
    }
}