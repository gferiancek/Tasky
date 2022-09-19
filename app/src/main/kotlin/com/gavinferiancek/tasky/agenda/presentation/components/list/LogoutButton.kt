package com.gavinferiancek.tasky.agenda.presentation.components.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.presentation.theme.LightBlue
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.gavinferiancek.tasky.core.presentation.theme.muted

/**
 * [IconButton] Composable that handles the logout action.
 * @param name Name to be displayed on the button. (Ideally user initials)
 * @param onLogout Action to be performed when clicking the Logout DropDownMenuItem.
 */
@Composable
fun LogoutButton(
    name: String,
    onLogout: () -> Unit,
) {
    val spacing = LocalSpacing.current
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    IconButton(
        modifier = androidx.compose.ui.Modifier.padding(spacing.small),
        onClick = { isExpanded = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_circle_48),
            tint = LightBlue,
            contentDescription = null,
        )
        Text(
            text = name,
            color = MaterialTheme.colors.muted
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onLogout()
                },
                content = { Text(text = stringResource(id = R.string.action_logout)) },
            )
        }
    }
}