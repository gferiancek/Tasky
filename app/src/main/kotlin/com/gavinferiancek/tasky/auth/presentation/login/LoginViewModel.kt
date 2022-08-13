package com.gavinferiancek.tasky.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gavinferiancek.tasky.auth.domain.validation.TextValidationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val textValidationManager: TextValidationManager
) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onTriggerEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.UpdateEmail -> {
                state = state.copy(
                    email = event.email,
                    isEmailValidated = textValidationManager.validateEmail(event.email),
                )
            }
            is LoginEvents.ToggleShowPassword -> {
                state = state.copy(
                    showPassword = event.showPassword,
                )
            }
            is LoginEvents.UpdatePassword -> {
                state = state.copy(
                    password = event.password,
                    isPasswordValidated = textValidationManager.validatePassword(event.password)
                )
            }
        }
    }
}