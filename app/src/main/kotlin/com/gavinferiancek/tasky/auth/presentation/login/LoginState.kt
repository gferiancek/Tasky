package com.gavinferiancek.tasky.auth.presentation.login

import com.gavinferiancek.tasky.auth.domain.validation.ValidationState
import com.gavinferiancek.tasky.core.util.UiText

data class LoginState(
    val email: String = "",
    val emailValidationStates: List<ValidationState> = listOf(ValidationState()),
    val password: String = "",
    val passwordValidationStates: List<ValidationState> = listOf(ValidationState()),
    val snackbarMessage: UiText? = null,
    val shouldShowPassword: Boolean = false,
    val shouldDisplayErrors: Boolean = false,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
)