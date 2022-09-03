package com.gavinferiancek.tasky.agenda.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.LocalDate

/**
 * Composable that takes in a list of [LocalDate]s and displays a ChipGroup for that data set.
 * @param modifier Modifier applied to DaySelector Row.
 * @param days List of [LocalDate]s to display as [DayChip]s.
 * @param selectedDay Index of the currently selected day.
 * @param onSelectDay Lambda to pass clicked [DayChip]'s [LocalDate] to parent Composable.
 */
@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    days: List<LocalDate>,
    selectedDay: LocalDate,
    onSelectDay: (LocalDate) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        userScrollEnabled = false,
    ) {
        items(
            items = days,
            key = { it }
        ) { currentDay ->
            DayChip(
                day = currentDay,
                isSelected = selectedDay == currentDay,
                onSelectDay = { day ->
                    if ( day != selectedDay) {
                        onSelectDay(day)
                    }
                }
            )
        }
    }
}