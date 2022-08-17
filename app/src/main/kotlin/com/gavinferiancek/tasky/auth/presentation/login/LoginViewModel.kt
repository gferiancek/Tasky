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
    private val validationManager: TextValidationManager
) : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onTriggerEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.ToggleShowPassword -> state = state.copy(shouldShowPassword = !state.shouldShowPassword)
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
            // TODO make API call
        } else {
            state = state.copy(shouldDisplayErrors = true)
        }
    }
}