package com.gavinferiancek.tasky.agenda.presentation.list

import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.agenda.domain.datetime.DateTimeManager
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.core.util.UiText
import java.time.LocalDate

data class AgendaListState(
    val name: String = "",
    val isLoading: Boolean = false,
    val pastItems: List<AgendaItem> = listOf(),
    val futureItems: List<AgendaItem> = listOf(),
    val initialDate: LocalDate = LocalDate.now(),
    val dayList: List<LocalDate> = DateTimeManager.generateDayList(initialDate),
    val listHeader: UiText = UiText.StringResource(id = R.string.date_today),
    val selectedDay: LocalDate = LocalDate.now(),
    val infoMessage: UiText? = null,
)