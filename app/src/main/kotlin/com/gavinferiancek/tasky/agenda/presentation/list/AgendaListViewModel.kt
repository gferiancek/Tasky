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
        viewModelScope.launch {
            getAgenda(
                time = System.currentTimeMillis()
            )
            state = state.copy(
                dayList = dateManager.generateDayList(state.initialDate),
                listHeader = generateListHeader(state.initialDate),
            )
        }
    }

    fun onTriggerEvent(event: AgendaListEvents) {
        state = when (event) {
            is AgendaListEvents.UpdateInitialDate -> {
                getAgenda(time = dateManager.localDateToMillis(event.date))
                state.copy(
                    initialDate = event.date,
                    dayList = dateManager.generateDayList(event.date),
                    listHeader = generateListHeader(event.date),
                    selectedDay = event.date,
                )
            }
            is AgendaListEvents.UpdateSelectedDay -> {
                getAgenda(time = dateManager.localDateToMillis(event.day))
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
        time: Long,
    ) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.getAgenda(
                time = time
            ).onSuccess { list ->
                val groupedItems = dateManager.groupAgendaItemByTime(list)
                state = state.copy(
                    pastItems = groupedItems.getOrDefault("pastItems", listOf()),
                    futureItems = groupedItems.getOrDefault("futureItems", listOf()),
                    isLoading = false,
                )
            }.onFailure { e ->
                state = state.copy(
                    isLoading = false,
                    pastItems = listOf(),
                    futureItems = listOf(),
                    infoMessage = e.getUiText(),
                )
            }
        }
    }
}