package com.gavinferiancek.tasky.agenda.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.gavinferiancek.tasky.core.presentation.theme.muted
import com.gavinferiancek.tasky.core.presentation.theme.selectedDay
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

/**
 * Composable that displays the name (Abbreviated as 1st letter of day) and day of the month of
 * the provided [LocalDate].
 * @param modifier Modifier applied to the Chip.
 * @param day [LocalDate] representation of the day to display.
 * @param index Position in the [DaySelector]'s List of [LocalDate]s
 * @param isSelected Determines whether this [DayChip] is the currently selected Day.
 * @param onSelectDay Lambda to pass index of clicked [DayChip] to parent Composable.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DayChip(
    modifier: Modifier = Modifier,
    day: LocalDate,
    index: Int,
    isSelected: Boolean,
    onSelectDay: (Int) -> Unit,
) {
    val spacing = LocalSpacing.current

    Chip(
        modifier = modifier
            .padding(
                start = spacing.extraSmall,
                end = spacing.extraSmall,
            ),
        onClick = { onSelectDay(index) },
        shape = CircleShape,
        colors = ChipDefaults.chipColors(
            backgroundColor = if (isSelected) MaterialTheme.colors.selectedDay else MaterialTheme.colors.surface,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = spacing.medium,
                    bottom = spacing.medium,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = remember {
                    day.dayOfWeek.getDisplayName(
                        TextStyle.NARROW,
                        Locale.getDefault()
                    )
                },
                color = if (isSelected) MaterialTheme.colors.onSurface else MaterialTheme.colors.muted,
            )
            Text(
                text = day.dayOfMonth.toString(),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h5
            )
        }
    }
}