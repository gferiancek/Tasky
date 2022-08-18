package com.gavinferiancek.tasky.auth.data.repository

import com.gavinferiancek.tasky.auth.data.remote.AuthApi
import com.gavinferiancek.tasky.auth.data.remote.login.LoginRequestDto
import com.gavinferiancek.tasky.auth.data.remote.login.toUserInfo
import com.gavinferiancek.tasky.auth.data.remote.register.RegisterRequestDto
import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository
import com.gavinferiancek.tasky.auth.domain.user.User

class AuthRepositoryImpl(
    private val authApi: AuthApi,
) : AuthRepository {

    override suspend fun loginUser(
        email: String,
        password: String,
    ): User {
        return authApi.loginUser(
            loginRequest = LoginRequestDto(
                email = email,
                password = password
            )
        ).toUserInfo()
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