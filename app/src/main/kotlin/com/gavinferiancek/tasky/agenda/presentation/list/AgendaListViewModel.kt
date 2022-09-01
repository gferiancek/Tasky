package com.gavinferiancek.tasky.agenda.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AgendaListViewModel @Inject constructor(
    private val dateManager: DateTimeManager,
) : ViewModel() {
    var state by mutableStateOf(AgendaListState())
        private set

    init {
        // TODO GET AgendaItems
        state = state.copy(
            dayList = dateManager.generateDayList(state.initialDate),
            listHeader = generateListHeader(state.initialDate),
            needleIndex = dateManager.calculateNeedleIndex(state.items), // TODO Replace state.items with fetched data
        )
    }

    fun onTriggerEvent(event: AgendaListEvents) {
        state = when (event) {
            is AgendaListEvents.UpdateInitialDate -> {
                // TODO GET AgendaItems for new initialDate
                state.copy(
                    initialDate = event.date,
                    dayList = dateManager.generateDayList(event.date),
                    listHeader = generateListHeader(event.date),
                    selectedDayIndex = 0, // Select the first day in DaySelector
                    needleIndex = dateManager.calculateNeedleIndex(state.items), // TODO Replace state.items with fetched data
                )
            }
            is AgendaListEvents.UpdateSelectedDay -> {
                // TODO GET AgendaItems for selectedDay
                state.copy(
                    listHeader = generateListHeader(state.dayList[event.index]),
                    selectedDayIndex = event.index,
                    needleIndex = dateManager.calculateNeedleIndex(state.items), // TODO Replace state.items with fetched data
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
}