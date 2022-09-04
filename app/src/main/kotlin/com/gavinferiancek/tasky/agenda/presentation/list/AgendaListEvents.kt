package com.gavinferiancek.tasky.agenda.presentation.list

import java.time.LocalDate

sealed class AgendaListEvents {

    object OnDismissSnackbar : AgendaListEvents()

    data class UpdateInitialDate(
        val date: LocalDate,
    ) : AgendaListEvents()

    data class UpdateSelectedDay(
        val day: LocalDate,
    ) : AgendaListEvents()
}