package com.gavinferiancek.tasky.agenda.presentation.components.list.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.presentation.theme.LimeGreen

/** Composable used to display Event Data
 * @param modifier [Modifier] passed to [AgendaListCard]
 * @param title [String] used in [AgendaListCard]'s title slot
 * @param description [String] used in [AgendaListCard]'s body slot.
 * @param timestamp [String] used in [AgendaListCard]'s footer slot.
 * @param onOpen Lambda used for the [AgendaListCard]'s Open action and Card click.
 * @param onEdit Lambda used for the [AgendaListCard]'s Edit action.
 * @param onDelete Lambda used for the [AgendaListCard]'s Delete action.
 */
@Composable
fun EventItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    timestamp: String,
    onOpen: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    AgendaListCard(
        modifier = modifier,
        backgroundColor = LimeGreen,
        contentColor = MaterialTheme.colors.onSurface,
        description = description,
        timestamp = timestamp,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // IconButton is used for consistency in padding/alignment with TaskItem
                IconButton(
                    enabled = false,
                    onClick = {},
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_circle_24),
                        tint = MaterialTheme.colors.onSurface,
                        contentDescription = null,
                    )
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.h2,
                )
            }
        },
        onOpen = { onOpen() },
        onEdit = { onEdit() },
        onDelete = { onDelete() },
    )
}