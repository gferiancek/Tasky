package com.gavinferiancek.tasky.agenda.presentation.components.list.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.presentation.theme.DarkGreen

/** Composable used to display Event Data
 * @param modifier [Modifier] passed to [AgendaListCard]
 * @param title [String] used in [AgendaListCard]'s title slot
 * @param description [String] used in [AgendaListCard]'s body slot.
 * @param timestamp [String] used in [AgendaListCard]'s footer slot.
 * @param isDone [Boolean] used to style title based on value.
 * @param onToggleIsDone Lambda trigged when clicking on Circle [Box] in title row.
 * @param onOpen Lambda used for the [CardMenuButton]'s Open action and Card click.
 * @param onEdit Lambda used for the [CardMenuButton]'s Edit action.
 * @param onDelete Lambda used for the [CardMenuButton]'s Delete action.
 */
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    timestamp: String,
    isDone: Boolean,
    onToggleIsDone: () -> Unit,
    onOpen: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    val iconResId = remember(isDone) {
        if (isDone) R.drawable.ic_outline_check_circle_24
        else R.drawable.ic_outline_circle_24
    }
    val textDecoration = remember(isDone) {
        if (isDone) TextDecoration.LineThrough
        else TextDecoration.None
    }

    AgendaListCard(
        modifier = modifier,
        backgroundColor = DarkGreen,
        contentColor = MaterialTheme.colors.onPrimary,
        description = description,
        timestamp = timestamp,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onToggleIsDone() }) {
                    Icon(
                        painter = painterResource(id = iconResId),
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.is_done_toggle_content_description),
                    )
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.h2,
                    textDecoration = textDecoration,
                )
            }
        },
        onOpen = { onOpen() },
        onEdit = { onEdit() },
        onDelete = { onDelete() },
    )
}