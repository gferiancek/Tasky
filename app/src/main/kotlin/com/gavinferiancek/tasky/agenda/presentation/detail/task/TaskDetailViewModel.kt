package com.gavinferiancek.tasky.agenda.presentation.detail.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gavinferiancek.tasky.agenda.domain.repository.AgendaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    repository: AgendaRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    var state by mutableStateOf(TaskDetailState())
        private set

    init {
        val id = savedStateHandle.get<String>("id")
        val editState = savedStateHandle.get<Boolean>("isEditing")
    }

    fun onTriggerEvent(event: TaskDetailEvents) {
        when (event) {
            is TaskDetailEvents.OnDismissSnackbar -> state = state.copy(infoMessage = null)
            is TaskDetailEvents.ToggleEditState -> state = state.copy(editState = !state.editState)
            is TaskDetailEvents.DeleteTask -> {

            }
            is TaskDetailEvents.SaveTask -> {

            }
        }
    }
}