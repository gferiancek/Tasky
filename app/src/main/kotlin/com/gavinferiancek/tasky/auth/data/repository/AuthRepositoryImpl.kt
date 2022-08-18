package com.gavinferiancek.tasky.auth.data.repository

import com.gavinferiancek.tasky.auth.data.remote.AuthApi
import com.gavinferiancek.tasky.auth.data.remote.login.LoginRequestDto
import com.gavinferiancek.tasky.auth.data.remote.register.RegisterRequestDto
import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi,
) : AuthRepository {

    override suspend fun loginUser(
        email: String,
        password: String,
    ) {
        val authorizedUser = authApi.loginUser(
            loginRequest = LoginRequestDto(
                email = email,
                password = password
            )
        )
        // TODO insert authorizedUser into DataStore
    }

    override suspend fun registerUser(
        fullName: String,
        email: String,
        password: String,
    ) {
        authApi.registerUser(
            registerRequest = RegisterRequestDto(
                fullName = fullName,
                email = email,
                password = password
            )
        )
    }
}