package com.gavinferiancek.tasky.auth.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gavinferiancek.tasky.auth.domain.validation.TextValidationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val textValidationManager: TextValidationManager
) : ViewModel() {
    var state by mutableStateOf(AuthState())
        private set

    fun onTriggerEvent(event: AuthEvents) {
        when (event) {
            is AuthEvents.ResetState -> state = AuthState()
            is AuthEvents.UpdateName -> {
                state = state.copy(
                    name = event.name,
                    isNameValidated = textValidationManager.validateName(event.name),
                )
            }
            is AuthEvents.UpdateEmail -> {
                state = state.copy(
                    email = event.email,
                    isEmailValidated = textValidationManager.validateEmail(event.email),
                )
            }
            is AuthEvents.ToggleShowPassword -> {
                state = state.copy(
                    showPassword = event.showPassword,
                )
            }
            is AuthEvents.UpdatePassword -> {
                state = state.copy(
                    password = event.password,
                )
            }
            is AuthEvents.ToggleShowRegister -> {
                state = state.copy(showRegister = event.showRegister)
            }
        }
    }
}