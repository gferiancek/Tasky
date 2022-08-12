package com.gavinferiancek.tasky.auth.domain.validation

class TextValidationManager {

    fun validateName(name: String) = name.length in 2..50

    fun validateEmail(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validatePassword(password: String): Boolean {
        return password.length >= 9 &&
                password.any { it.isDigit() } &&
                password.any { it.isLowerCase() } &&
                password.any { it.isUpperCase() }
    }
}