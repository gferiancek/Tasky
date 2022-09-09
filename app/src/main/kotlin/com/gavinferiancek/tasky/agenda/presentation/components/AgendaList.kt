package com.gavinferiancek.tasky.agenda.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gavinferiancek.tasky.agenda.domain.model.AgendaItem
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import java.time.format.DateTimeFormatter

@Composable
fun AgendaList(
    pastItems: List<AgendaItem>,
    futureItems: List<AgendaItem>,
    hasData: Boolean
) {
    val spacing = LocalSpacing.current
    val agendaItemFormatter = remember { DateTimeFormatter.ofPattern("MMM d, HH:mm") }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(
            items = pastItems,
            key = { item ->
                item.id
            }
        ) { item ->
            //TODO Replace Text with AgendaItem Composable
            Text(text = remember(item.startTime) { item.startTime.format(agendaItemFormatter) })
            Spacer(modifier = Modifier.height(spacing.small))
        }
        item {
            TimeNeedle(isVisible = hasData)
            Spacer(modifier = Modifier.height(spacing.small))
        }
        items(
            items = futureItems,
            key = { item ->
                item.id
            }
        ) { item ->
            //TODO Replace Text with AgendaItem Composable
            Text(text = remember(item.startTime) { item.startTime.format(agendaItemFormatter) })
            Spacer(modifier = Modifier.height(spacing.small))
        }
    }
}