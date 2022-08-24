package com.gavinferiancek.tasky.core.presentation.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun showSnackbar(
    scaffoldState: ScaffoldState,
    message: String?,
    label: String,
    onDismiss: () -> Unit,
    action: () -> Unit = {},
) {
    if (message != null) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = label,
            )
            if (snackbarResult == SnackbarResult.ActionPerformed) action()
            onDismiss()
        }
    }
}