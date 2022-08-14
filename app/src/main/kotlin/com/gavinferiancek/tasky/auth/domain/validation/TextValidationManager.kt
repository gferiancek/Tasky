package com.gavinferiancek.tasky.auth.domain.validation

import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.util.UiText

/**
 * Manager class to handle text validation in LoginScreen.kt and RegisterScreen.kt
 *
 * NOTE: Even though [validateName] and [validateEmail] can only have one active validation state,
 * those functions still return lists. UI is already required to handle a list for the password validation
 * states, so if we ever wanted that to be true for name/email we'd only need to change code here.
 */
class TextValidationManager(
    private val emailMatcher: EmailMatcher,
) {

    fun validateName(name: String): List<ValidationState> {
        return listOf(
            when {
                name.count() < 2 || name.count() > 50 -> ValidationState(
                    isValidated = false,
                    message = UiText.StringResource(R.string.error_invalid_name_length)
                )
                else -> ValidationState(isValidated = true)
            }
        )
    }

    fun validateEmail(email: String): List<ValidationState> {
        return listOf(
            when {
                emailMatcher.matches(email) -> ValidationState(isValidated = true)
                email.isBlank() -> ValidationState(
                    isValidated = false,
                    message = UiText.StringResource(R.string.error_email_blank)
                )
                else -> ValidationState(
                    isValidated = false,
                    message = UiText.StringResource(R.string.error_invalid_email)
                )
            }
        )
    }

    fun validatePassword(password: String): List<ValidationState> {
        return listOf(
            ValidationState(
                isValidated = password.count() >= 9,
                message = UiText.StringResource(R.string.password_length_requirement)
            ),
            ValidationState(
                isValidated = password.any { it.isDigit() },
                message = UiText.StringResource(R.string.password_digit_requirement)
            ),
            ValidationState(
                isValidated = password.any { it.isLowerCase() },
                message = UiText.StringResource(R.string.password_lowercase_requirement)
            ),
            ValidationState(
                isValidated = password.any { it.isUpperCase() },
                message = UiText.StringResource(R.string.password_uppercase_requirement)
            ),
        )
    }
}