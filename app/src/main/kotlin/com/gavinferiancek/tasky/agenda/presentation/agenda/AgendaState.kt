package com.gavinferiancek.tasky.agenda.presentation.agenda

import java.time.LocalDate

data class AgendaState(
    val initialDate: LocalDate = LocalDate.now(),
    val dayList: List<LocalDate> = listOf(),
    val selectedDayIndex: Int = 0,
)