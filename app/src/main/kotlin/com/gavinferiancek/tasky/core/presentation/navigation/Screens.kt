package com.gavinferiancek.tasky.core.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(
    val route: String,
    val arguments: List<NamedNavArgument>,
) {
    object Login : Screens(
        route = "login",
        arguments = emptyList(),
    )

    object Register : Screens(
        route = "register",
        arguments = emptyList(),
    )

    object AgendaList : Screens(
        route = "agendaList",
        arguments = emptyList(),
    )

    object EventDetail: Screens(
        route = "eventDetail",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            },
            navArgument("isEditing") {
                type = NavType.BoolType
            },
        ),
    )

    object TaskDetail: Screens(
        route = "taskDetail",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = true
            },
            navArgument("isEditing") {
                type = NavType.BoolType
            },
        ),
    )

    object ReminderDetail: Screens(
        route = "reminderDetail",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            },
            navArgument("isEditing") {
                type = NavType.BoolType
            },
        ),
    )
}
