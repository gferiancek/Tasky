package com.gavinferiancek.tasky.agenda.presentation.agenda

import androidx.compose.runtime.Composable
import com.gavinferiancek.tasky.agenda.presentation.components.DaySelector
import com.gavinferiancek.tasky.core.presentation.components.CardLayout

@Composable
fun AgendaScreen(
    state: AgendaState,
    events: (AgendaEvents) -> Unit,
) {
    CardLayout(
        header = {
        }
    ) {
        DaySelector(
            days = state.days,
            selectedDay = state.selectedDay,
            onSelectDay = { index ->
                events(AgendaEvents.UpdateSelectedDay(index))
            }
        )
    }
}