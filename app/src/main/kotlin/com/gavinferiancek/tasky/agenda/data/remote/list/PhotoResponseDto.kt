package com.gavinferiancek.tasky.agenda.data.remote.list

import com.gavinferiancek.tasky.agenda.data.local.database.entity.PhotoEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponseDto(
    @Json(name = "key")
    val key: String,
    @Json(name = "url")
    val url: String,
)

fun PhotoResponseDto.toPhotoEntity(eventId: String): PhotoEntity {
    return PhotoEntity(
        photoId = key,
        eventSourceId = eventId,
        url = url,
    )
}

fun List<PhotoResponseDto>.toPhotoEntityList(eventId: String): List<PhotoEntity> {
    return map { it.toPhotoEntity(eventId = eventId) }
}