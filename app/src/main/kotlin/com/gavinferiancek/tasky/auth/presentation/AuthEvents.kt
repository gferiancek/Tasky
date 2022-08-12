package com.gavinferiancek.tasky.auth.presentation

sealed class AuthEvents {

    object ResetState : AuthEvents()

    data class UpdateName(
        val name: String,
    ) : AuthEvents()

    data class UpdateEmail(
        val email: String,
    ) : AuthEvents()

    data class ToggleShowPassword(
        val showPassword: Boolean,
    ) : AuthEvents()

    data class UpdatePassword(
        val password: String,
    ) : AuthEvents()

    data class ToggleShowRegister(
        val showRegister: Boolean,
    ) : AuthEvents()
}
