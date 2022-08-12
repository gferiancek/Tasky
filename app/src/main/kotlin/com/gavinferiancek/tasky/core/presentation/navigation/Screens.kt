package com.gavinferiancek.tasky.core.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Screens(
    val route: String,
    val arguments: List<NamedNavArgument>,
) {
    object Login : Screens(
        route = "login",
        arguments = emptyList(),
    )
}