package com.gavinferiancek.tasky.auth.presentation.register

data class RegisterState(
    val name: String = "",
    val isNameValidated: Boolean = false,
    val email: String = "",
    val isEmailValidated: Boolean = false,
    val password: String = "",
    val isPasswordValidated: Boolean = false,
    val showPassword: Boolean = false,
)
