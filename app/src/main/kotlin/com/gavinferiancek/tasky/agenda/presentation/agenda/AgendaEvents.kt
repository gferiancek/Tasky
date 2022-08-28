package com.gavinferiancek.tasky.agenda.presentation.agenda

import java.time.LocalDate

sealed class AgendaEvents {

    data class UpdateInitialDate(
        val date: LocalDate,
    ) : AgendaEvents()

    data class UpdateSelectedDay(
        val index: Int,
    ) : AgendaEvents()
}