package com.gavinferiancek.tasky.agenda.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ListHeader(
    header: String,
) {
    AnimatedContent(
        targetState = header,
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow,
                )
            ).plus(fadeIn()).with(
                fadeOut()
            )
        }
    ) {
        Text(
            text = it,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h2,
        )
    }
}