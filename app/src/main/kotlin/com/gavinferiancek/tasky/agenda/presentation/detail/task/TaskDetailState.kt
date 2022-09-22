package com.gavinferiancek.tasky.agenda.presentation.detail.task

import com.gavinferiancek.tasky.agenda.domain.model.Task
import com.gavinferiancek.tasky.core.util.UiText

data class TaskDetailState(
    val editState: Boolean = false,
    val task: Task = Task(),
    val infoMessage: UiText? = null,
)
