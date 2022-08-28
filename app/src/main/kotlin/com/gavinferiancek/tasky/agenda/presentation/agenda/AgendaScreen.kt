package com.gavinferiancek.tasky.agenda.presentation.agenda

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gavinferiancek.tasky.agenda.presentation.components.AgendaHeader
import com.gavinferiancek.tasky.agenda.presentation.components.DaySelector
import com.gavinferiancek.tasky.agenda.presentation.components.TimeNeedle
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import java.time.format.DateTimeFormatter

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
            modifier = Modifier.fillMaxWidth(),
            days = state.dayList,
            selectedDay = state.selectedDayIndex,
            onSelectDay = { index ->
                events(AgendaEvents.UpdateSelectedDay(index))
            }
        )
        Spacer(modifier = Modifier.height(spacing.large))

        Text(
            text = state.listHeader.asString(),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h2,
        )
        Spacer(modifier = Modifier.height(spacing.medium))

        val agendaItemFormatter = remember { DateTimeFormatter.ofPattern("MMM, HH:mm") }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            itemsIndexed(
                items = state.items,
            ) { index, item ->
                // TODO Replace Text with AgendaItem Composable
                Text(text = remember { item.startTime.format(agendaItemFormatter) })
                Spacer(modifier = Modifier.height(spacing.small))

                if (state.needleIndex == index) TimeNeedle(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}