package com.gavinferiancek.tasky.auth.domain.validation

import com.gavinferiancek.tasky.core.util.UiText

data class ValidationState(
    val isValidated: Boolean = false,
    val message: UiText? = null,
)
