package com.gavinferiancek.tasky.auth.presentation.login

sealed class LoginEvents {

    object ToggleShowPassword : LoginEvents()

    object Submit : LoginEvents()

    data class UpdateEmail(
        val email: String,
    ) : LoginEvents()

    data class UpdatePassword(
        val password: String,
    ) : LoginEvents()
}
