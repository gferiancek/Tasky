package com.gavinferiancek.tasky.auth.presentation.login

sealed class LoginEvents {

    data class UpdateEmail(
        val email: String,
    ) : LoginEvents()

    data class ToggleShowPassword(
        val showPassword: Boolean,
    ) : LoginEvents()

    data class UpdatePassword(
        val password: String,
    ) : LoginEvents()
}
