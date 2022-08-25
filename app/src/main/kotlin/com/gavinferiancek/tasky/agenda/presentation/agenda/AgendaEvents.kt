package com.gavinferiancek.tasky.agenda.presentation.agenda

sealed class AgendaEvents {

    data class UpdateSelectedDay(
        val index: Int,
    ) : AgendaEvents()
}