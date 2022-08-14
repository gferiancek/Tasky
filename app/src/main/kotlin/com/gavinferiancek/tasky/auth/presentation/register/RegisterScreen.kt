package com.gavinferiancek.tasky.auth.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.auth.presentation.components.EmailTextField
import com.gavinferiancek.tasky.auth.presentation.components.NameTextField
import com.gavinferiancek.tasky.auth.presentation.components.PasswordTextField
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing

@Composable
fun RegisterScreen(
    state: RegisterState,
    events: (RegisterEvents) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val spacing = LocalSpacing.current

    CardLayout(
        header = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.register_header),
                    style = MaterialTheme.typography.h2,
                    color = contentColorFor(backgroundColor = MaterialTheme.colors.background),
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            item {
                Spacer(modifier = Modifier.height(spacing.large))

                NameTextField(
                    name = state.name,
                    validationStates = state.nameValidationState,
                    displayErrors = state.displayErrors,
                    onUpdateName = { name ->
                        events(RegisterEvents.UpdateName(name))
                    }
                )
                EmailTextField(
                    email = state.email,
                    validationStates = state.emailValidationState,
                    displayErrors = state.displayErrors,
                    onUpdateEmail = { email ->
                        events(RegisterEvents.UpdateEmail(email))
                    }
                )
                PasswordTextField(
                    password = state.password,
                    validationStates = state.passwordValidationStates,
                    showPassword = state.showPassword,
                    displayErrors = state.displayErrors,
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
}