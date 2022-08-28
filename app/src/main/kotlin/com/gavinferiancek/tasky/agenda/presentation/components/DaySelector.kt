package com.gavinferiancek.tasky.agenda.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
    LazyVerticalGrid(
        columns = GridCells.Fixed(days.count()),
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        itemsIndexed(
            items = days,
            key = { _, day ->
                day.dayOfYear
            }
        ) { dayIndex, day ->
            DayChip(
                day = day,
                index = dayIndex,
                isSelected = selectedDay == dayIndex,
                onSelectDay = { index ->
                    if (index != selectedDay) {
                        onSelectDay(index)
                    }
                }
            )
        }
    }
}