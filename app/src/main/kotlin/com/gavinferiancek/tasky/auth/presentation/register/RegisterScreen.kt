package com.gavinferiancek.tasky.auth.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.auth.presentation.components.AuthHeader
import com.gavinferiancek.tasky.auth.presentation.components.EmailTextField
import com.gavinferiancek.tasky.auth.presentation.components.NameTextField
import com.gavinferiancek.tasky.auth.presentation.components.PasswordTextField
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.components.CircularIndeterminateProgressBar
import com.gavinferiancek.tasky.core.presentation.components.showSnackbar
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing

@Composable
fun RegisterScreen(
    state: RegisterState,
    scaffoldState: ScaffoldState,
    events: (RegisterEvents) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val spacing = LocalSpacing.current

    CardLayout(
        header = { AuthHeader(title = stringResource(id = R.string.register_header)) },
    ) {
        if(state.isLoading) CircularIndeterminateProgressBar(modifier = Modifier.fillMaxWidth())
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            item {
                Spacer(modifier = Modifier.height(spacing.large))

                NameTextField(
                    name = state.name,
                    validationStates = state.nameValidationState,
                    shouldDisplayErrors = state.shouldDisplayErrors,
                    onUpdateName = { name ->
                        events(RegisterEvents.UpdateName(name))
                    }
                )
                EmailTextField(
                    email = state.email,
                    validationStates = state.emailValidationState,
                    shouldDisplayErrors = state.shouldDisplayErrors,
                    onUpdateEmail = { email ->
                        events(RegisterEvents.UpdateEmail(email))
                    }
                )
                PasswordTextField(
                    password = state.password,
                    validationStates = state.passwordValidationStates,
                    shouldShowPassword = state.shouldShowPassword,
                    shouldDisplayErrors = state.shouldDisplayErrors,
                    onToggleShowPassword = { events(RegisterEvents.ToggleShowPassword) },
                    onUpdatePassword = { password ->
                        events(RegisterEvents.UpdatePassword(password))
                    },
                    onDone = { events(RegisterEvents.Submit) }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { events(RegisterEvents.Submit) },
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(text = stringResource(id = R.string.register_button))
                }
            }
            item {
                Button(
                    onClick = {
                        onNavigateUp()
                    },
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(
                                top = spacing.small,
                                bottom = spacing.small,
                            ),
                        painter = painterResource(id = R.drawable.ic_arrow_back_ios_new_24),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary,
                    )
                }
                Spacer(modifier = Modifier.height(spacing.medium))
            }
        }
    }
    showSnackbar(
        scaffoldState = scaffoldState,
        message = state.infoMessage?.asString(),
        label = stringResource(id = R.string.action_ok),
        onDismiss = { events(RegisterEvents.SnackbarDismissed) },
        action = { if (state.hasCreatedAccount) onNavigateUp() }
    )
}