package com.gavinferiancek.tasky.agenda.presentation.components.list.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing

/**
 * Composable that contains the base structure and styling logic for our AgendaListItems.
 * @param modifier [Modifier] applied to the [Card] Composable.
 * @param backgroundColor [Color] to apply to [Card] backgroundColor param.
 * @param contentColor [Color] to apply to content inside the [Card] composable.
 * @param description [String] used in [Card] body
 * @param formattedTime [String] displayed at bottom of card.
 * @param title Slot for Composable displayed in the top row of the [Card]
 * @param onOpen Lambda for [CardMenuButton]'s Open action as well as the [Card]'s onClick action.
 * @param onEdit Lambda for [CardMenuButton]'s Edit action
 * @param onDelete Lambda for [CardMenuButton]'s Delete action
 */
@Composable
fun AgendaListCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    description: String,
    formattedTime: String,
    title: @Composable () -> Unit,
    onOpen: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = modifier
            .clickable { onOpen() },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
    ) {
        Column(
            modifier = Modifier.padding(
                top = spacing.small,
                start = spacing.small,
                end = spacing.medium,
                bottom = spacing.small,
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                title()
                CardMenuButton(
                    onOpen = onOpen,
                    onEdit = onEdit,
                    onDelete = onDelete
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = spacing.extraLarge),
                text = description,
                style = MaterialTheme.typography.body1,
            )
            Spacer(modifier = Modifier.height(spacing.medium))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = formattedTime,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End,
            )
        }
    }
}