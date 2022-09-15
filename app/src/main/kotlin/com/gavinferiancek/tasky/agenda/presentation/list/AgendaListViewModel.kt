package com.gavinferiancek.tasky.agenda.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.repository.AgendaRepository
import com.gavinferiancek.tasky.core.data.remote.error.getUiText
import com.gavinferiancek.tasky.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AgendaListViewModel @Inject constructor(
    private val dateManager: DateTimeManager,
    private val repository: AgendaRepository,
) : ViewModel() {
    var state by mutableStateOf(AgendaListState())
        private set

    init {
        getAgenda(
            timestamp = System.currentTimeMillis(),
            date = state.selectedDay
        )
        state = state.copy(
            dayList = dateManager.generateDayList(state.initialDate),
            listHeader = generateListHeader(state.initialDate),
        )
    }

    fun onTriggerEvent(event: AgendaListEvents) {
        state = when (event) {
            is AgendaListEvents.OnDismissSnackbar -> state.copy(infoMessage = null)
            is AgendaListEvents.UpdateInitialDate -> {
                getAgenda(
                    timestamp = dateManager.localDateToMillis(event.date),
                    date = event.date,
                )
                state.copy(
                    initialDate = event.date,
                    dayList = dateManager.generateDayList(event.date),
                    listHeader = generateListHeader(event.date),
                    selectedDay = event.date,
                )
            }
            is AgendaListEvents.UpdateSelectedDay -> {
                getAgenda(
                    timestamp = dateManager.localDateToMillis(event.day),
                    date = event.day,
                )
                state.copy(
                    listHeader = generateListHeader(event.day),
                    selectedDay = event.day,
                )
            }
        }
    }

    private fun generateListHeader(
        selectedDay: LocalDate,
    ): UiText {
        val currentDay = LocalDate.now()
        return when (selectedDay) {
            currentDay.minusDays(1) -> UiText.StringResource(id = R.string.date_yesterday)
            currentDay -> UiText.StringResource(id = R.string.date_today)
            currentDay.plusDays(1) -> UiText.StringResource(id = R.string.date_tomorrow)
            else -> {
                val formatter = DateTimeFormatter.ofPattern("E d, u")
                UiText.DynamicString(selectedDay.format(formatter))
            }
        }
    }

    private fun getAgenda(
        timestamp: Long,
        date: LocalDate,
    ) {
        getAgendaFromCache(date = date)
        fetchAgenda(timestamp = timestamp)
    }

    private fun fetchAgenda(timestamp: Long) {
        // TODO Only fetch if connected to internet. Else display offline mode indication
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.fetchAgendaForDate(timestamp).onFailure { e ->
                state = state.copy(infoMessage = e.getUiText())
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun getAgendaFromCache(date: LocalDate) {
        repository.getCachedAgendaForDate(
            date = date
        ).onEach { list ->
            val groupedItems = dateManager.groupAgendaItemByTime(list)
            state = state.copy(
                pastItems = groupedItems.getOrDefault("pastItems", listOf()),
                futureItems = groupedItems.getOrDefault("futureItems", listOf()),
            )
        }.launchIn(viewModelScope)
    }
}