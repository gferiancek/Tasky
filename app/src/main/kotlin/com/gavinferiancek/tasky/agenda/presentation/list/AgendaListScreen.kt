package com.gavinferiancek.tasky.agenda.presentation.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.presentation.components.AgendaHeader
import com.gavinferiancek.tasky.agenda.presentation.components.DaySelector
import com.gavinferiancek.tasky.agenda.presentation.components.EmptyText
import com.gavinferiancek.tasky.agenda.presentation.components.TimeNeedle
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.components.CircularIndeterminateProgressBar
import com.gavinferiancek.tasky.core.presentation.components.showSnackbar
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import java.time.format.DateTimeFormatter

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

        Crossfade(targetState = state.isLoading) { targetState ->
            when (targetState) {
                true -> CircularIndeterminateProgressBar(modifier = Modifier.fillMaxSize())
                else -> {
                    if (state.pastItems.isEmpty() && state.futureItems.isEmpty()) EmptyText()
                    else {
                        val agendaItemFormatter =
                            remember { DateTimeFormatter.ofPattern("MMM, HH:mm") }
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            items(
                                items = state.pastItems,
                                key = { item ->
                                    item.id
                                }
                            ) { item ->
                                // TODO Replace Text with AgendaItem Composable
                                Text(text = item.startTime.format(agendaItemFormatter))
                                Spacer(modifier = Modifier.height(spacing.small))
                            }
                            item {
                                TimeNeedle()
                                Spacer(modifier = Modifier.height(spacing.small))
                            }
                            items(
                                items = state.futureItems,
                                key = { item ->
                                    item.id
                                }
                            ) { item ->
                                // TODO Replace Text with AgendaItem Composable
                                Text(text = item.startTime.format(agendaItemFormatter))
                                Spacer(modifier = Modifier.height(spacing.small))
                            }
                        }
                    }
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