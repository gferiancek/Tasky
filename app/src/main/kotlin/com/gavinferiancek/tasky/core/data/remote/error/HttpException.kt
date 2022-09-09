package com.gavinferiancek.tasky.core.data.remote.error

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.HttpException

fun HttpException.extractExceptionMessage(): String? {
    // Trying to parse 429 will result in an EOF exception
    if (code() == 429) return null
    val adapter: JsonAdapter<ErrorResponseDto> =
        Moshi.Builder().build().adapter(ErrorResponseDto::class.java)

    val errorResponse = response()?.run {
        errorBody()?.let { body ->
            adapter.fromJson(body.string())
        }
    }
    return errorResponse?.message
}