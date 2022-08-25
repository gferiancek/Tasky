package com.gavinferiancek.tasky.agenda.presentation.agenda

import androidx.compose.runtime.Composable
import com.gavinferiancek.tasky.agenda.presentation.components.AgendaHeader
import com.gavinferiancek.tasky.agenda.presentation.components.DaySelector
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing

@Composable
fun AgendaScreen(
    state: AgendaState,
    events: (AgendaEvents) -> Unit,
) {
    val spacing = LocalSpacing.current

    CardLayout(
        header = {
            AgendaHeader(
                initialDate = state.initialDate,
                onSelectDate = { date ->
                    events(AgendaEvents.UpdateInitialDate(date))
                }
            )
        }
    ) {
        DaySelector(
            days = state.dayList,
            selectedDay = state.selectedDayIndex,
            onSelectDay = { index ->
                events(AgendaEvents.UpdateSelectedDay(index))
            }
        )
    }
}