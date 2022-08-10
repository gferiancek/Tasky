package com.gavinferiancek.tasky.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = DarkGreen,
    onPrimary = White,
    secondary = LimeGreen,
    secondaryVariant = LightGray,
    onSecondary = Black,
    background = Black,
    surface = DarkGray,
    onSurface = White,
)

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = DarkGreen,
    onPrimary = White,
    secondary = LimeGreen,
    secondaryVariant = LightGray,
    onSecondary = Black,
    background = Black,
    surface = White,
    onSurface = Black,
)

val Colors.mutedText: Color
    get() = MutedText

val Colors.selectedDate: Color
    get() = Yellow

@Composable
fun TaskyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}