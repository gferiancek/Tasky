package com.gavinferiancek.tasky

import com.gavinferiancek.tasky.core.presentation.navigation.Screens


data class MainState(
    val isLoading: Boolean = false,
    val startDestination: String = Screens.Login.route
)
