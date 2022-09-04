package com.gavinferiancek.tasky.agenda.presentation.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gavinferiancek.tasky.agenda.presentation.components.*
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.components.CircularIndeterminateProgressBar
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import java.time.format.DateTimeFormatter

@Composable
fun AgendaScreen(
    state: AgendaListState,
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

        ListHeader(header = state.listHeader.asString())
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
    }
}