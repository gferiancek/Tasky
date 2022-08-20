package com.gavinferiancek.tasky.auth.data.repository

import com.gavinferiancek.tasky.auth.data.remote.AuthApi
import com.gavinferiancek.tasky.auth.data.remote.login.LoginRequestDto
import com.gavinferiancek.tasky.auth.data.remote.register.RegisterRequestDto
import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository
import com.gavinferiancek.tasky.core.data.remote.error.getUiText
import com.gavinferiancek.tasky.core.domain.datastore.UserStore
import com.gavinferiancek.tasky.core.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import java.util.concurrent.CancellationException

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val userStore: UserStore,
) : AuthRepository {

    override suspend fun loginUser(
        email: String,
        password: String,
    ): Flow<DataState<Unit>> = flow {
        emit(DataState.Loading(isLoading = true))
        val authorizedUser = authApi.loginUser(
            loginRequest = LoginRequestDto(
                email = email,
                password = password,
            )
        )
        // TODO Insert user credentials into DataStore
        emit(DataState.Success(Unit))
    }.catch { e ->
        if (e is CancellationException) throw e
        else emit(DataState.Error(uiText = e.getUiText()))
    }.onCompletion {
        emit(DataState.Loading(isLoading = false))
    }

    override suspend fun registerUser(
        fullName: String,
        email: String,
        password: String,
    ): Flow<DataState<Unit>> = flow {
        emit(DataState.Loading(isLoading = true))
        authApi.registerUser(
            registerRequest = RegisterRequestDto(
                fullName = fullName,
                email = email,
                password = password,
            )
        )
        emit(DataState.Success(Unit))
    }.catch { e ->
        if (e is CancellationException) throw e
        else emit(DataState.Error(uiText = e.getUiText()))
    }.onCompletion {
        emit(DataState.Loading(isLoading = false))
    }
}