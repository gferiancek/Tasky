package com.gavinferiancek.tasky.agenda.presentation.list

import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.agenda.domain.model.Task
import java.time.LocalDate

sealed class AgendaListEvents {

    object OnDismissSnackbar : AgendaListEvents()

    object OnRefresh : AgendaListEvents()

    data class UpdateInitialDate(
        val date: LocalDate,
    ) : AgendaListEvents()

    data class UpdateSelectedDay(
        val day: LocalDate,
    ) : AgendaListEvents()

    data class UpdateTask(
        val task: Task,
    ) : AgendaListEvents()

    data class DeleteAgendaItem(
        val item: AgendaItem,
    ) : AgendaListEvents()
}