package com.gavinferiancek.tasky.agenda.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun TimeNeedle(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier = Modifier
                .size(10.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.primary,
            content = {},
        )
        Surface(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth(),
            shape = RectangleShape,
            color = MaterialTheme.colors.primary,
            content =  {},
        )
    }
}