package com.gavinferiancek.tasky.auth.presentation.login

data class LoginState(
    val email: String = "",
    val isEmailValidated: Boolean = false,
    val password: String = "",
    val isPasswordValidated: Boolean = false,
    val showPassword: Boolean = false,
)
