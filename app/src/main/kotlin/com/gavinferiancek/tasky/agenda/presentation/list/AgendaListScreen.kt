package com.gavinferiancek.tasky.agenda.presentation.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.presentation.components.AgendaHeader
import com.gavinferiancek.tasky.agenda.presentation.components.AgendaList
import com.gavinferiancek.tasky.agenda.presentation.components.DaySelector
import com.gavinferiancek.tasky.agenda.presentation.components.EmptyText
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.components.CircularIndeterminateProgressBar
import com.gavinferiancek.tasky.core.presentation.components.showSnackbar
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing

@Composable
fun AgendaScreen(
    state: AgendaListState,
    scaffoldState: ScaffoldState,
    events: (AgendaListEvents) -> Unit,
) {
    val spacing = LocalSpacing.current

    CardLayout(
        header = {
            AgendaHeader(
                initialDate = state.initialDate,
                onSelectDate = { date ->
                    events(AgendaListEvents.UpdateInitialDate(date))
                }
            )
        }
    ) {
        DaySelector(
            modifier = Modifier.fillMaxWidth(),
            days = state.dayList,
            selectedDay = state.selectedDay,
            onSelectDay = { day ->
                events(AgendaListEvents.UpdateSelectedDay(day))
            }
        )
        Spacer(modifier = Modifier.height(spacing.large))

        Text(
            text = state.listHeader.asString(),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h2,
        )
        Spacer(modifier = Modifier.height(spacing.medium))

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            if (state.isLoading) CircularIndeterminateProgressBar(modifier = Modifier.fillMaxWidth())
            Crossfade(targetState = state.hasData) { targetState ->
                when (targetState) {
                    true -> {
                        AgendaList(
                            pastItems = state.pastItems,
                            futureItems = state.futureItems,
                            hasData = state.hasData,
                        )
                    }
                    else -> EmptyText()
                }
            }
        }
        showSnackbar(
            scaffoldState = scaffoldState,
            message = state.infoMessage?.asString(),
            label = stringResource(id = R.string.action_ok),
            onDismiss = { events(AgendaListEvents.OnDismissSnackbar) })
    }
}