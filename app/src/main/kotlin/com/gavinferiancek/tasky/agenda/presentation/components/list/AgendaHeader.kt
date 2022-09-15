package com.gavinferiancek.tasky.agenda.presentation.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

/**
 * Composable that displays a header for the AgendaScreen.
 * @param modifier Modifier applied to [Row] that encapsulates AgendaHeader.
 * @param initialDate Starting date that is used as a basis for the [TextButton]'s text as well
 * as the default selected date for the [datepicker].
 * @param onSelectDate Lambda to pass [LocalDate] selected in [datepicker] to parent Composable.
 */
@Composable
fun AgendaHeader(
    modifier: Modifier = Modifier,
    initialDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit,
) {
    val spacing = LocalSpacing.current
    val dialogState = rememberMaterialDialogState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = spacing.medium,
                end = spacing.medium,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = { dialogState.show() },
        ) {
            Text(
                text = initialDate.month.name,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.button
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = stringResource(R.string.header_dropdown_content_description),
                tint = MaterialTheme.colors.onPrimary,
            )
        }
    }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(stringResource(R.string.action_ok))
            negativeButton(stringResource(R.string.action_cancel))
        }
    ) {
        datepicker(
            initialDate = initialDate,
            colors = DatePickerDefaults.colors(
                calendarHeaderTextColor = MaterialTheme.colors.onSurface,
                dateInactiveTextColor = MaterialTheme.colors.onSurface
            )
        ) { date ->
            onSelectDate(date)
        }
    }
}