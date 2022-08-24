package com.gavinferiancek.tasky.core.domain.util

import com.gavinferiancek.tasky.core.util.UiText

sealed class DataState<T> {
    data class Success<T>(val data: T?) : DataState<T>()
    data class Error<T>(val uiText: UiText, val data: T? = null) : DataState<T>()
    data class Loading<T>(val isLoading: Boolean = false) : DataState<T>()
}
