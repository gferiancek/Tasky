package com.gavinferiancek.tasky.agenda.presentation.components.list.item

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.presentation.theme.LightBlue

/**
 * Composable that responds to button clicks with a popup of context actions.
 * @param onOpen Action to be performed when clicking on the Open DropDownMenuItem.
 * @param onEdit Action to be performed when clicking on the Edit DropDownMenuItem.
 * @param onDelete Action to be performed when clicking on the Delete DropDownMenuItem.
 */
@Composable
fun CardMenuButton(
    onOpen: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    IconButton(onClick = { isExpanded = true }) {
        Icon(
            modifier = Modifier
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_baseline_more_horiz_24),
            contentDescription = stringResource(id = R.string.list_item_menu_content_description),
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onOpen()
                },
                content = { Text(stringResource(id = R.string.action_open)) },
            )
            Divider(
                thickness = 1.dp,
                color = LightBlue
            )
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onEdit()
                },
                content = { Text(text = stringResource(id = R.string.action_edit)) },
            )
            Divider(
                thickness = 1.dp,
                color = LightBlue
            )
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onDelete()
                },
                content = { Text(text = stringResource(id = R.string.action_delete)) },
            )
        }
    }
}