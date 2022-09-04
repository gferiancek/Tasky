package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.gavinferiancek.tasky.agenda.domain.model.Photo
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = AgendaEntity::class,
            parentColumns = ["id"],
            childColumns = ["event_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class PhotoEntity(
    @PrimaryKey @ColumnInfo(name = "photo_id")
    val photoId: String,
    @NotNull @ColumnInfo(name = "event_id")
    val eventId: String,
    @NotNull @ColumnInfo(name = "url")
    val url: String,
)

fun PhotoEntity.toPhoto(): Photo {
    return Photo(
        key = photoId,
        url = url,
    )
}

fun List<PhotoEntity>.toPhotoList(): List<Photo> {
    return map { it.toPhoto() }
}
