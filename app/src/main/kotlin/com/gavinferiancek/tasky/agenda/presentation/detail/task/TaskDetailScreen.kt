package com.gavinferiancek.tasky.agenda.presentation.detail.task

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.components.showSnackbar

@Composable
fun TaskDetailScreen(
    state: TaskDetailState,
    events: (TaskDetailEvents) -> Unit,
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
) {
    CardLayout(
        header = {}
    ) {

    }
    showSnackbar(
        scaffoldState = scaffoldState,
        message = state.infoMessage?.asString(),
        label = stringResource(id = R.string.action_ok),
        onDismiss = { events(TaskDetailEvents.OnDismissSnackbar) },
    )
}
