package com.gavinferiancek.tasky.core.data.remote.error

import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.util.UiText
import retrofit2.HttpException

fun Throwable.getUiText(): UiText {
    val message = when (this) {
        is HttpException -> extractExceptionMessage()
        else -> message
    }
    return if (message != null) UiText.DynamicString(value = message)
    else UiText.StringResource(R.string.error_unknown)
}