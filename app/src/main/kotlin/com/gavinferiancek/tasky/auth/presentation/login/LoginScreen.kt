package com.gavinferiancek.tasky.auth.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.auth.presentation.components.AuthHeader
import com.gavinferiancek.tasky.auth.presentation.components.EmailTextField
import com.gavinferiancek.tasky.auth.presentation.components.PasswordTextField
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.components.CircularIndeterminateProgressBar
import com.gavinferiancek.tasky.core.presentation.components.showSnackbar
import com.gavinferiancek.tasky.core.presentation.theme.LightBlue
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.gavinferiancek.tasky.core.presentation.theme.muted

@Composable
fun LoginScreen(
    state: LoginState,
    scaffoldState: ScaffoldState,
    events: (LoginEvents) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToAgenda: () -> Unit,
) {
    LaunchedEffect(state.isLoggedIn) { if (state.isLoggedIn) onNavigateToAgenda() }
    val spacing = LocalSpacing.current

    CardLayout(
        header = { AuthHeader(title = stringResource(id = R.string.login_header)) },
    ) {
        CircularIndeterminateProgressBar(isLoading = state.isLoading)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            item {
                Spacer(modifier = Modifier.height(spacing.large))

                EmailTextField(
                    email = state.email,
                    validationStates = state.emailValidationStates,
                    shouldDisplayErrors = state.shouldDisplayErrors,
                    onUpdateEmail = { email ->
                        events(LoginEvents.UpdateEmail(email))
                    }
                )
                PasswordTextField(
                    password = state.password,
                    validationStates = state.passwordValidationStates,
                    shouldShowPassword = state.shouldShowPassword,
                    shouldDisplayErrors = state.shouldDisplayErrors,
                    onToggleShowPassword = { events(LoginEvents.ToggleShowPassword) },
                    onUpdatePassword = { password ->
                        events(LoginEvents.UpdatePassword(password))
                    },
                    onDone = { events(LoginEvents.Submit) }
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { events(LoginEvents.Submit) },
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(text = stringResource(id = R.string.login_button))
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.signup_base_text),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.muted
                    )
                    ClickableText(
                        onClick = { onNavigateToRegister() },
                        text = AnnotatedString(
                            text = stringResource(id = R.string.signup_clickable_text),
                            spanStyle = SpanStyle(
                                color = LightBlue,
                            )
                        ),
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
                Spacer(modifier = Modifier.height(spacing.medium))
            }
        }
    }
    showSnackbar(
        scaffoldState = scaffoldState,
        message = state.infoMessage?.asString(),
        label = stringResource(id = R.string.snackbar_action_ok),
        onDismiss = { events(LoginEvents.SnackbarDismissed) },
    )
}