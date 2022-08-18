package com.gavinferiancek.tasky.auth.domain.authorization

import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository
import com.gavinferiancek.tasky.auth.domain.user.User
import com.gavinferiancek.tasky.core.data.remote.error.convertToUiText
import com.gavinferiancek.tasky.core.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import java.util.concurrent.CancellationException

class AuthManager(
    private val authRepository: AuthRepository,
) {

    fun loginUser(
        email: String,
        password: String,
    ): Flow<DataState<User>> = flow {
        emit(DataState.Loading(isLoading = true))
        val userInfo = authRepository.loginUser(
            email = email,
            password = password,
        )
        emit(DataState.Success(data = userInfo))
    }.catch { e ->
        if (e is CancellationException) throw e
        else emit(DataState.Error(uiText = e.convertToUiText()))
    }.onCompletion {
        emit(DataState.Loading(isLoading = false))
    }

    fun registerUser(
        fullName: String,
        email: String,
        password: String,
    ): Flow<DataState<Unit>> = flow {
        emit(DataState.Loading(isLoading = true))
        authRepository.registerUser(
            fullName = fullName,
            email = email,
            password = password,
        )
        emit(DataState.Success(Unit))
    }.catch { e ->
        if (e is CancellationException) throw e
        else emit(DataState.Error(uiText = e.convertToUiText()))
    }.onCompletion {
        emit(DataState.Loading(isLoading = false))
    }
}
