package com.gavinferiancek.tasky.auth.presentation

data class AuthState(
    val name: String = "",
    val isNameValidated: Boolean = false,
    val email: String = "",
    val isEmailValidated: Boolean = false,
    val password: String = "",
    val showPassword: Boolean = false,
    val showRegister: Boolean = false,
)
