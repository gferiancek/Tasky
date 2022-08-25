package com.gavinferiancek.tasky.core.presentation.theme

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
    onBackground = White,
    surface = DarkGray,
    onSurface = White,
    error = Red,
    onError = White,
)

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = DarkGreen,
    onPrimary = White,
    secondary = LimeGreen,
    secondaryVariant = LightGray,
    onSecondary = Black,
    background = Black,
    onBackground = White,
    surface = White,
    onSurface = Black,
    error = Red,
    onError = White,
)

// Android docs recommends extension properties if only adding a few colors to the theme.
// Not sure how to get this warning to disappear without using CompositionLocalProvider
// instead of using extension property.
val Colors.muted: Color
    get() = Muted

val Colors.selectedDay: Color
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