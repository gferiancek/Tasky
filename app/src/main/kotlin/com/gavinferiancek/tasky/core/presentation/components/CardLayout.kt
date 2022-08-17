package com.gavinferiancek.tasky.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing

/**
 * Base layout used throughout the app. (Includes header and a Card to hold the content.)
 * @param modifier Modifier to be applied to Column that encapsulates [content].
 * @param header Slot for Composable to be displayed above the Card Composable.
 * @param content Slot for Composable to be displayed inside of the Card Composable.
 */
@Composable
fun CardLayout(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        header()
        Card(
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(
                topStart = spacing.large,
                topEnd = spacing.large,
                bottomStart = spacing.none,
                bottomEnd = spacing.none,
            ),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(spacing.medium),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                content()
            }
        }
    }
}