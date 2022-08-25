package com.gavinferiancek.tasky.agenda.presentation.agenda

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gavinferiancek.tasky.agenda.domain.localdate.LocalDateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    dateManager: LocalDateManager,
) : ViewModel() {
    var state by mutableStateOf(AgendaState())
        private set

    init {
        state = state.copy(days = dateManager.generateDayList(LocalDate.now()))
        // TODO GET AgendaItems
    }

    fun onTriggerEvent(event: AgendaEvents) {
        when (event) {
            is AgendaEvents.UpdateSelectedDay -> state = state.copy(selectedDay = event.index)
        }
    }
}