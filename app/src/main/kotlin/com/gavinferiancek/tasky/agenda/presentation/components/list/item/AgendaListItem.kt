package com.gavinferiancek.tasky.agenda.presentation.components.list.item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gavinferiancek.tasky.agenda.domain.NavigationOptions
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Event
import com.gavinferiancek.tasky.agenda.domain.model.Reminder
import com.gavinferiancek.tasky.agenda.domain.model.Task
import java.time.format.DateTimeFormatter

/**
 * Wrapper Composable that takes in an [AgendaItem] and composes the correct Item for that AgendaItem type.
 * @param modifier Modifier passed to [EventItem]/[TaskItem]/[ReminderItem]
 * @param item [AgendaItem] containing the data for the item to be composed.
 * @param formatter [DateTimeFormatter] used to format startTime and endTimes.
 * @param setIsDone Lambda to pass Task back to VM with a toggled isDone state.
 * @param onNavigateToEventDetail Lambda used to navigate to the Event detail screen with supplied NavigationOptions.
 * @param onNavigateToTaskDetail Lambda used to navigate to the Task detail screen with supplied NavigationOptions.
 * @param onNavigateToReminderDetail Lambda used to navigate to the Reminder detail screen with supplied NavigationOptions.
 * @param onDelete Lambda used for the [AgendaListCard]'s Delete DropDownMenuItem action.
 */
@Composable
fun AgendaListItem(
    modifier: Modifier = Modifier,
    item: AgendaItem,
    formatter: DateTimeFormatter,
    setIsDone: (Task) -> Unit,
    onNavigateToEventDetail: (NavigationOptions) -> Unit,
    onNavigateToTaskDetail: (NavigationOptions) -> Unit,
    onNavigateToReminderDetail: (NavigationOptions) -> Unit,
    onDelete: () -> Unit,
) {
    when (item) {
        is Event -> {
            EventItem(
                modifier = modifier,
                title = item.title,
                description = item.description,
                timestamp = remember(item.startTime, item.endTime) {
                    "${item.startTime.format(formatter)} - ${item.endTime.format(formatter)}"
                },
                onOpen = {
                    onNavigateToEventDetail(
                        NavigationOptions(
                            id = item.id,
                            isEditing = false,
                        )
                    )
                },
                onEdit = {
                    onNavigateToEventDetail(
                        NavigationOptions(
                            id = item.id,
                            isEditing = true,
                        )
                    )
                },
                onDelete = { onDelete() },
            )
        }
        is Task -> {
            TaskItem(
                modifier = modifier,
                title = item.title,
                description = item.description,
                timestamp = remember(item.startTime) { item.startTime.format(formatter) },
                isDone = item.isDone,
                setIsDone = { setIsDone(item.copy(isDone = !item.isDone)) },
                onOpen = {
                    onNavigateToTaskDetail(
                        NavigationOptions(
                            id = item.id,
                            isEditing = false,
                        )
                    )
                },
                onEdit = {
                    onNavigateToTaskDetail(
                        NavigationOptions(
                            id = item.id,
                            isEditing = true,
                        )
                    )
                },
                onDelete = { onDelete() },
            )
        }
        is Reminder -> {
            ReminderItem(
                modifier = modifier,
                title = item.title,
                description = item.description,
                timestamp = remember(item.startTime) { item.startTime.format(formatter) },
                onOpen = {
                    onNavigateToReminderDetail(
                        NavigationOptions(
                            id = item.id,
                            isEditing = false,
                        )
                    )
                },
                onEdit = {
                    onNavigateToReminderDetail(
                        NavigationOptions(
                            id = item.id,
                            isEditing = true,
                        )
                    )
                },
                onDelete = { onDelete() },
            )
        }
    }
}