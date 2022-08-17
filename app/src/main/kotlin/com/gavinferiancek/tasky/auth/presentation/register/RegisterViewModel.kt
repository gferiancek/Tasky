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
    private val validationManager: TextValidationManager
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    fun onTriggerEvent(event: RegisterEvents) {
        when (event) {
            is RegisterEvents.ToggleShowPassword -> state =
                state.copy(shouldShowPassword = !state.shouldShowPassword)
            is RegisterEvents.UpdateName -> {
                state = state.copy(
                    name = event.name,
                    nameValidationState = validationManager.validateName(event.name),
                )
            }
            is RegisterEvents.UpdateEmail -> {
                state = state.copy(
                    email = event.email,
                    emailValidationState = validationManager.validateEmail(event.email),
                )
            }
            is RegisterEvents.UpdatePassword -> {
                state = state.copy(
                    password = event.password,
                    passwordValidationStates = validationManager.validatePassword(event.password),
                )
            }
            is RegisterEvents.Submit -> submit()
        }
    }

    private fun submit() {
        state = state.copy(
            nameValidationState = validationManager.validateName(state.name),
            emailValidationState = validationManager.validateEmail(state.email),
            passwordValidationStates = validationManager.validatePassword(state.password)
        )
        if (state.nameValidationState.all { it.isValid } &&
            state.emailValidationState.all { it.isValid } &&
            state.passwordValidationStates.all { it.isValid }
        ) {
            // TODO Make API Call
        } else {
            state = state.copy(shouldDisplayErrors = true)
        }
    }
}