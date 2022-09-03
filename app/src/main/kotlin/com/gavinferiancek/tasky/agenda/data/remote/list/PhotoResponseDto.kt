package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.domain.model.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponseDto(
    @Json(name = "key")
    val key: String,
    @Json(name = "url")
    val url: String,
)

fun PhotoResponseDto.toPhoto(): Photo {
    return Photo(
        key = key,
        url = url,
    )
}

fun List<PhotoResponseDto>.toPhotoList(): List<Photo> {
    return map { it.toPhoto() }
}
