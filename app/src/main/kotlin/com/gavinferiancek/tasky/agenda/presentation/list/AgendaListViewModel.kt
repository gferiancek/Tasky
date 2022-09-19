package com.gavinferiancek.tasky.agenda.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Event
import com.gavinferiancek.tasky.agenda.domain.model.Reminder
import com.gavinferiancek.tasky.agenda.domain.model.Task
import com.gavinferiancek.tasky.agenda.domain.repository.AgendaRepository
import com.gavinferiancek.tasky.core.data.remote.error.getUiText
import com.gavinferiancek.tasky.core.domain.User
import com.gavinferiancek.tasky.core.domain.getInitials
import com.gavinferiancek.tasky.core.domain.preferences.UserPreferences
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
    userPreferences: UserPreferences,
) : ViewModel() {
    var state by mutableStateOf(AgendaListState(name = userPreferences.getUser().getInitials()))
        private set

    init { getAgenda(date = state.selectedDay) }

    fun onTriggerEvent(event: AgendaListEvents) {
        when (event) {
            is AgendaListEvents.OnDismissSnackbar -> state = state.copy(infoMessage = null)
            is AgendaListEvents.OnRefresh -> getAgenda(date = state.selectedDay)
            is AgendaListEvents.UpdateInitialDate -> {
                getAgenda(date = event.date)
                state = state.copy(
                    initialDate = event.date,
                    dayList = dateManager.generateDayList(event.date),
                    listHeader = generateListHeader(event.date),
                    selectedDay = event.date,
                )
            }
            is AgendaListEvents.UpdateSelectedDay -> {
                getAgenda(date = event.day)
                state = state.copy(
                    listHeader = generateListHeader(event.day),
                    selectedDay = event.day,
                )
            }
            is AgendaListEvents.UpdateTask -> updateTask(task = event.task)
            is AgendaListEvents.DeleteAgendaItem -> deleteAgendaItem(item = event.item)
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

    private fun getAgenda(date: LocalDate) {
        getAgendaFromCache(date = date)
        fetchAgenda(timestamp = dateManager.localDateToMillis(date))
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

    private fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
                .onFailure { e ->
                    state = state.copy(
                        infoMessage = e.getUiText()
                    )
                }
        }
    }

    private fun deleteAgendaItem(item: AgendaItem) {
        viewModelScope.launch {
            when (item) {
                is Event -> {
                    repository.deleteEvent(id = item.id)
                        .onFailure { e ->
                            state = state.copy(
                                infoMessage = e.getUiText()
                            )
                        }
                }
                is Task -> {
                    repository.deleteTask(id = item.id)
                        .onFailure { e ->
                            state = state.copy(
                                infoMessage = e.getUiText()
                            )
                        }
                }
                is Reminder -> {
                    repository.deleteReminder(id = item.id)
                        .onFailure { e ->
                            state = state.copy(
                                infoMessage = e.getUiText()
                            )
                        }
                }
            }
        }
    }
}