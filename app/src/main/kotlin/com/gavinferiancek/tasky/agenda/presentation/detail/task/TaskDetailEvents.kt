package com.gavinferiancek.tasky.agenda.presentation.detail.task

import com.gavinferiancek.tasky.agenda.domain.model.Task

sealed class TaskDetailEvents {

    object DeleteTask: TaskDetailEvents()

    object OnDismissSnackbar: TaskDetailEvents()

    object ToggleEditState: TaskDetailEvents()

    data class SaveTask(
        val task: Task,
    ): TaskDetailEvents()
}
