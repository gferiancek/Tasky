package com.gavinferiancek.tasky.agenda.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.Attendee
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "attendees",
    foreignKeys = [
        ForeignKey(
            entity = EventEntity::class,
            parentColumns = ["event_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class AttendeeEntity(
    @PrimaryKey @ColumnInfo(name = "user_id")
    val userId: String,
    @NotNull @ColumnInfo(name = "email")
    val email: String,
    @NotNull @ColumnInfo(name = "full_name")
    val fullName: String,
    @NotNull @ColumnInfo(name = "event_id_ref")
    val eventIdRef: String,
    @NotNull @ColumnInfo(name = "is_going")
    val isGoing: Boolean,
    @NotNull @ColumnInfo(name = "remind_at")
    val remindAt: Long,
)

fun AttendeeEntity.toAttendee(): Attendee {
    return Attendee(
        email = email,
        fullName = fullName,
        userId = userId,
        eventId = eventIdRef,
        isGoing = isGoing,
        remindAt = DateTimeManager.millisToZonedDateTime(remindAt),
    )
}

fun List<AttendeeEntity>.toAttendeeList(): List<Attendee> {
    return map { it.toAttendee() }
}