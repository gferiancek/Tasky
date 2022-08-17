package com.gavinferiancek.tasky.auth.presentation.login

import com.gavinferiancek.tasky.auth.domain.validation.ValidationState

data class LoginState(
    val email: String = "",
    val emailValidationStates: List<ValidationState> = listOf(ValidationState()),
    val password: String = "",
    val passwordValidationStates: List<ValidationState> = listOf(ValidationState()),
    val shouldShowPassword: Boolean = false,
    val shouldDisplayErrors: Boolean = false,
)