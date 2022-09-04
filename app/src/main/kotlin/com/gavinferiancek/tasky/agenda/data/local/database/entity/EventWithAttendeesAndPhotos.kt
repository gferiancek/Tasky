package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Event

data class EventWithAttendeesAndPhotos(
    @Embedded
    val event: EventEntity,
    @Relation(
        parentColumn = "event_id",
        entityColumn = "event_id_ref",
    )
    val attendees: List<AttendeeEntity>,
    @Relation(
        parentColumn = "event_id",
        entityColumn = "event_source_id"
    )
    val photos: List<PhotoEntity>
)

fun EventWithAttendeesAndPhotos.toEvent(): Event {
    return Event(
        id = event.eventId,
        title = event.title,
        description = event.description,
        startTime = DateTimeManager.millisToZonedDateTime(event.startTime),
        remindAt = DateTimeManager.millisToZonedDateTime(event.remindAt),
        endTime = DateTimeManager.millisToZonedDateTime(event.endTime),
        hostId = event.hostId,
        isCreator = event.isCreator,
        attendees = attendees.toAttendeeList(),
        photos = photos.toPhotoList()
    )
}

fun List<EventWithAttendeesAndPhotos>.toEventList(): List<Event> {
    return map { it.toEvent() }
}