package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Event

data class EventWithAttendeesAndPhotos(
    @Embedded
    val event: EventEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "event_id",
    )
    val attendees: List<AttendeeEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "event_id"
    )
    val photos: List<PhotoEntity>,
)

fun EventWithAttendeesAndPhotos.toAgendaItem(): AgendaItem {
    return Event(
        id = event.id,
        title = event.title,
        description = event.description,
        startTime = DateTimeManager.millisToZonedDateTime(event.startTime),
        remindAt = DateTimeManager.millisToZonedDateTime(event.remindAt),
        endTime = DateTimeManager.millisToZonedDateTime(event.endTime),
        hostId = event.hostId,
        isCreator = event.isCreator,
        attendees = attendees.toAttendeeList(),
        photos = photos.toPhotoList(),
    )
}

fun List<EventWithAttendeesAndPhotos>.toAgendaItemList(): List<AgendaItem> {
    return map { it.toAgendaItem() }
}