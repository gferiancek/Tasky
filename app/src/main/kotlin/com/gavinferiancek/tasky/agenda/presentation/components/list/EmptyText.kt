package com.gavinferiancek.tasky.agenda.presentation.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.gavinferiancek.tasky.core.presentation.theme.muted

@Composable
fun EmptyText() {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_event_note_48),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(spacing.small))
        Text(
            text = stringResource(id = R.string.empty_text),
            color = MaterialTheme.colors.muted,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
        )
    }
}