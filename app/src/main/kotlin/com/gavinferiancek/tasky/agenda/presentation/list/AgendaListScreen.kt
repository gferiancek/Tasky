package com.gavinferiancek.tasky.agenda.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.presentation.components.list.AgendaHeader
import com.gavinferiancek.tasky.agenda.presentation.components.list.AgendaList
import com.gavinferiancek.tasky.agenda.presentation.components.list.DaySelector
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.components.showSnackbar
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun AgendaScreen(
    state: AgendaListState,
    events: (AgendaListEvents) -> Unit,
) {
    val spacing = LocalSpacing.current
    val scaffoldState = rememberScaffoldState()

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

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isLoading),
            onRefresh = { events(AgendaListEvents.OnRefresh) }
        ) {
            AgendaList(
                pastItems = state.pastItems,
                futureItems = state.futureItems,
            )
        }
    }
    showSnackbar(
        scaffoldState = scaffoldState,
        message = state.infoMessage?.asString(),
        label = stringResource(id = R.string.action_ok),
        onDismiss = { events(AgendaListEvents.OnDismissSnackbar) })
}