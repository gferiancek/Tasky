package com.gavinferiancek.tasky.agenda.presentation.agenda

import java.time.LocalDate

data class AgendaState(
    val days: List<LocalDate> = listOf(),
    val selectedDay: Int = 0,
)