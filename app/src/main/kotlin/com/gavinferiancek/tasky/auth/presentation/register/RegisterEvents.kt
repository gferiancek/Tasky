package com.gavinferiancek.tasky.auth.presentation.register

sealed class RegisterEvents {

    object ToggleShowPassword : RegisterEvents()

    object Submit : RegisterEvents()

    data class UpdateName(
        val name: String,
    ) : RegisterEvents()

    data class UpdateEmail(
        val email: String,
    ) : RegisterEvents()

    data class UpdatePassword(
        val password: String,
    ) : RegisterEvents()
}