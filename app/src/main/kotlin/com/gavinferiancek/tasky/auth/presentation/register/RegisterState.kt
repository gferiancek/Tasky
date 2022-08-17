package com.gavinferiancek.tasky.auth.presentation.register

import com.gavinferiancek.tasky.auth.domain.validation.ValidationState

data class RegisterState(
    val name: String = "",
    val nameValidationState: List<ValidationState> = listOf(ValidationState()),
    val email: String = "",
    val emailValidationState: List<ValidationState> = listOf(ValidationState()),
    val password: String = "",
    val passwordValidationStates: List<ValidationState> = listOf(ValidationState()),
    val shouldShowPassword: Boolean = false,
    val shouldDisplayErrors: Boolean = false,
)
