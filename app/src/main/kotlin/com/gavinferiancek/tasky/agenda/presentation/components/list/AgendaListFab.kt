package com.gavinferiancek.tasky.agenda.presentation.components.list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.domain.NavigationOptions

/**
 * FAB Composable displayed on the AgendaListScreen
 * @param onNavigateToEventDetail Lambda that navigates with the provided id, and edit state.
 * @param onNavigateToTaskDetail Lambda that navigates with the provided id, and edit state.
 * @param onNavigateToReminderDetail Lambda that navigates with the provided id, and edit state.
 * (Note: FAB is used to create new items, so the id is always null and isEditing is always true.)
 */
@Composable
fun AgendaListFab(
    onNavigateToEventDetail: (NavigationOptions) -> Unit,
    onNavigateToTaskDetail: (NavigationOptions) -> Unit,
    onNavigateToReminderDetail: (NavigationOptions) -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    FloatingActionButton(
        onClick = { isExpanded = true },
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onNavigateToEventDetail(
                        NavigationOptions(
                            id = null,
                            isEditing = true,
                        )
                    )
                },
                content = { Text(text = stringResource(R.string.action_event)) }
            )
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onNavigateToTaskDetail(
                        NavigationOptions(
                            id = null,
                            isEditing = true,
                        )
                    )
                },
                content = { Text(text = stringResource(R.string.action_task)) }
            )
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onNavigateToReminderDetail(
                        NavigationOptions(
                            id = null,
                            isEditing = true,
                        )
                    )
                },
                content = { Text(text = stringResource(R.string.action_reminder)) }
            )
        }
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.agenda_list_fab_content_description)
        )
    }
}