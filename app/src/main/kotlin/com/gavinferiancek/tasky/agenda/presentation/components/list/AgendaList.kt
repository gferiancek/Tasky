package com.gavinferiancek.tasky.agenda.presentation.components.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gavinferiancek.tasky.agenda.domain.NavigationOptions
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Task
import com.gavinferiancek.tasky.agenda.presentation.components.list.item.AgendaListItem
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import java.time.format.DateTimeFormatter

/**
 * Composable that displays a list of AgendaItems.
 * @param modifier Modifier applied to LazyColumn.
 * @param pastItems [List]<[AgendaItem]> that contains a startTime before the current time.
 * @param futureItems [List]<[AgendaItem]> that contains a startTime after the current time.
 * @param onToggleIsDone Lambda to pass [Task] back to VM after toggling the isDone value.
 * @param onDelete Lambda to pass AgendaItem back to VM after clicking on it's Delete action.
 * @param onNavigateToEventDetail Lambda used to navigate to the Event detail screen with supplied NavigationOptions.
 * @param onNavigateToTaskDetail Lambda used to navigate to the Task detail screen with supplied NavigationOptions.
 * @param onNavigateToReminderDetail Lambda used to navigate to the Reminder detail screen with supplied NavigationOptions.

 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgendaList(
    modifier: Modifier = Modifier,
    pastItems: List<AgendaItem>,
    futureItems: List<AgendaItem>,
    onToggleIsDone: (Task) -> Unit,
    onDelete: (AgendaItem) -> Unit,
    onNavigateToEventDetail: (NavigationOptions) -> Unit,
    onNavigateToTaskDetail: (NavigationOptions) -> Unit,
    onNavigateToReminderDetail: (NavigationOptions) -> Unit,
) {
    val spacing = LocalSpacing.current
    val hasData = remember(pastItems, futureItems) {
        derivedStateOf { pastItems.isNotEmpty() || futureItems.isNotEmpty() }
    }
    // Provided here and passed to AgendaListItem so one instance is used for the all lists.
    val dateFormatter = remember { DateTimeFormatter.ofPattern("MMM d, HH:mm") }

    Crossfade(targetState = hasData.value) { targetState ->
        when (targetState) {
            true -> {
                LazyColumn(modifier = modifier) {
                    items(
                        items = pastItems,
                        key = { it.id }
                    ) { item ->
                        AgendaListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItemPlacement(),
                            item = item,
                            formatter = dateFormatter,
                            onToggleIsDone = { task ->
                                onToggleIsDone(task)
                            },
                            onNavigateToEventDetail = onNavigateToEventDetail,
                            onNavigateToTaskDetail = onNavigateToTaskDetail,
                            onNavigateToReminderDetail = onNavigateToReminderDetail,
                            onDelete = { onDelete(item) },
                        )
                        Spacer(modifier = Modifier.height(spacing.small))
                    }

                    item {
                        TimeNeedle(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItemPlacement(),
                            isVisible = hasData.value
                        )
                        Spacer(modifier = Modifier.height(spacing.small))
                    }

                    items(
                        items = futureItems,
                        key = { it.id }
                    ) { item ->
                        AgendaListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItemPlacement(),
                            item = item,
                            formatter = dateFormatter,
                            onToggleIsDone = { task ->
                                onToggleIsDone(task)
                            },
                            onNavigateToEventDetail = onNavigateToEventDetail,
                            onNavigateToTaskDetail = onNavigateToTaskDetail,
                            onNavigateToReminderDetail = onNavigateToReminderDetail,
                            onDelete = { onDelete(item) },
                        )
                        Spacer(modifier = Modifier.height(spacing.small))
                    }
                }
            }
            false -> {
                LazyColumn(
                    modifier = modifier,
                    verticalArrangement = Arrangement.Center,
                ) {
                    item { EmptyText() }
                }
            }
        }
    }
}