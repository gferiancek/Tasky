package com.gavinferiancek.tasky.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gavinferiancek.tasky.auth.domain.validation.TextValidationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val textValidationManager: TextValidationManager
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    fun onTriggerEvent(event: RegisterEvents) {
        when (event) {
            is RegisterEvents.UpdateName -> {
                state = state.copy(
                    name = event.name,
                    isNameValidated = textValidationManager.validateName(event.name)
                )
            }
            is RegisterEvents.UpdateEmail -> {
                state = state.copy(
                    email = event.email,
                    isEmailValidated = textValidationManager.validateEmail(event.email),
                )
            }
            is RegisterEvents.ToggleShowPassword -> {
                state = state.copy(
                    showPassword = event.showPassword,
                )
            }
            is RegisterEvents.UpdatePassword -> {
                state = state.copy(
                    password = event.password,
                    isPasswordValidated = textValidationManager.validatePassword(event.password)
                )
            }
        }
    }
}