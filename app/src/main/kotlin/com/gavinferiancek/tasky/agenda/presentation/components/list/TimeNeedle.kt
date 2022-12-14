package com.gavinferiancek.tasky.agenda.presentation.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

/**
 * Composable used to show current time in relation to AgendaList items.
 * @param modifier Modifier applied to [Row] that encapsulates the TimeNeedle.
 * @param isVisible Used to determine whether or not the TimeNeedle should be drawn.
 */
@Composable
fun TimeNeedle(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
) {
    // Used to avoid jank on Crossfade animation from populated to empty list. (Without this check an
    // empty list with TimeNeedle is drawn and then that is faded out to the EmptyListText() )
    if (isVisible) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary),
            )
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary),
            )
        }
    }
}