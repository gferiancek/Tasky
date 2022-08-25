package com.gavinferiancek.tasky.agenda.presentation.agenda

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val dateManager: DateTimeManager,
) : ViewModel() {
    var state by mutableStateOf(AgendaState())
        private set

    init {
        // TODO GET AgendaItems
        state = state.copy(
            dayList = dateManager.generateDayList(state.initialDate),
            needleIndex = dateManager.calculateNeedleIndex(state.items), // TODO Replace state.items with fetched data
        )
    }

    fun onTriggerEvent(event: AgendaEvents) {
        state = when (event) {
            is AgendaEvents.UpdateInitialDate -> {
                // TODO GET AgendaItems for new initialDate
                state.copy(
                    initialDate = event.date,
                    dayList = dateManager.generateDayList(event.date),
                    selectedDayIndex = 0, // Select the first day in DaySelector
                    needleIndex = dateManager.calculateNeedleIndex(state.items), // TODO Replace state.items with fetched data
                )
            }
            is AgendaEvents.UpdateSelectedDay -> {
                // TODO GET AgendaItems for selectedDay
                state.copy(
                    selectedDayIndex = event.index,
                    needleIndex = dateManager.calculateNeedleIndex(state.items), // TODO Replace state.items with fetched data
                )
            }
        }
    }
}