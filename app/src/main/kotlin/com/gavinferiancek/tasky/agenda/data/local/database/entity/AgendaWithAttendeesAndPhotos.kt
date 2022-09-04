package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Event
import com.gavinferiancek.tasky.agenda.domain.model.Reminder
import com.gavinferiancek.tasky.agenda.domain.model.Task

data class AgendaWithAttendeesAndPhotos(
    @Embedded
    val agendaItem: AgendaEntity,
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

fun AgendaWithAttendeesAndPhotos.toAgendaItem(): AgendaItem {
    return when (agendaItem.type) {
        AgendaEntity.Type.EVENT -> {
            Event(
                id = agendaItem.id,
                title = agendaItem.title,
                description = agendaItem.description,
                startTime = DateTimeManager.millisToZonedDateTime(agendaItem.startTime),
                remindAt = DateTimeManager.millisToZonedDateTime(agendaItem.remindAt),
                endTime = DateTimeManager.millisToZonedDateTime(agendaItem.endTime ?: 0),
                hostId = agendaItem.hostId ?: "",
                isCreator = agendaItem.isCreator ?: false,
                attendees = attendees.toAttendeeList(),
                photos = photos.toPhotoList(),
            )
        }
        AgendaEntity.Type.TASK -> {
            Task(
                id = agendaItem.id,
                title = agendaItem.title,
                description = agendaItem.description,
                startTime = DateTimeManager.millisToZonedDateTime(agendaItem.startTime),
                remindAt = DateTimeManager.millisToZonedDateTime(agendaItem.remindAt),
                isDone = agendaItem.isDone ?: false
            )
        }
        AgendaEntity.Type.REMINDER -> {
            Reminder(
                id = agendaItem.id,
                title = agendaItem.title,
                description = agendaItem.description,
                startTime = DateTimeManager.millisToZonedDateTime(agendaItem.startTime),
                remindAt = DateTimeManager.millisToZonedDateTime(agendaItem.remindAt),
            )
        }
    }
}

fun List<AgendaWithAttendeesAndPhotos>.toAgendaItemList(): List<AgendaItem> {
    return map { it.toAgendaItem() }
}