package com.gavinferiancek.tasky.agenda.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.LocalDate

/**
 * Composable that takes in a list of [LocalDate]s and displays a ChipGroup for that data set.
 * @param modifier Modifier applied to DaySelector Row.
 * @param days List of [LocalDate]s to display as [DayChip]s.
 * @param selectedDay Index of the currently selected day.
 * @param onSelectDay Lambda to pass index of clicked [DayChip] to parent Composable.
 */
@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    days: List<LocalDate>,
    selectedDay: Int,
    onSelectDay: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        days.forEachIndexed { currentDay, day ->
            DayChip(
                modifier = Modifier
                    .weight(1f),
                day = day,
                index = currentDay,
                isSelected = currentDay == selectedDay,
                onSelectDay = { index ->
                    onSelectDay(index)
                },
            )
        }
    }
}