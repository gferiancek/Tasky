package com.gavinferiancek.tasky.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gavinferiancek.tasky.auth.domain.repository.AuthRepository
import com.gavinferiancek.tasky.auth.domain.validation.TextValidationManager
import com.gavinferiancek.tasky.core.domain.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationManager: TextValidationManager,
    private val authRepository: AuthRepository,
) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set


    fun onTriggerEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.SnackbarDismissed -> state = state.copy(snackbarMessage = null)
            is LoginEvents.ToggleShowPassword -> state =
                state.copy(shouldShowPassword = !state.shouldShowPassword)
            is LoginEvents.UpdateEmail -> {
                state = state.copy(
                    email = event.email,
                    emailValidationStates = validationManager.validateEmail(event.email)
                )
            }
            is LoginEvents.UpdatePassword -> {
                state = state.copy(
                    password = event.password,
                    passwordValidationStates = validationManager.validatePassword(event.password)
                )
            }
            is LoginEvents.Submit -> submit()
        }
    }

    private fun submit() {
        state = state.copy(
            emailValidationStates = validationManager.validateEmail(state.email),
            passwordValidationStates = validationManager.validatePassword(state.password)
        )
        if (state.emailValidationStates.all { it.isValid } &&
            state.passwordValidationStates.all { it.isValid }
        ) {
            loginUser()
        } else {
            state = state.copy(shouldDisplayErrors = true)
        }
    }

    private fun loginUser() {
        viewModelScope.launch {
            authRepository.loginUser(
                email = state.email,
                password = state.password,
            ).onEach { dataState ->
                state = when (dataState) {
                    is DataState.Loading -> state.copy(isLoading = dataState.isLoading)
                    is DataState.Success -> state.copy(isLoggedIn = true)
                    is DataState.Error -> state.copy(snackbarMessage = dataState.uiText)
                }
            }.collect()
        }
    }
}