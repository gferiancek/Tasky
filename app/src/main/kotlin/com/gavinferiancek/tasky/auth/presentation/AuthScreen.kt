package com.gavinferiancek.tasky.auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.auth.presentation.components.RoundedTextField
import com.gavinferiancek.tasky.core.presentation.components.CardLayout
import com.gavinferiancek.tasky.core.presentation.theme.LightBlue
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.gavinferiancek.tasky.core.presentation.theme.muted

@Composable
fun AuthScreen(
    state: AuthState,
    events: (AuthEvents) -> Unit,
) {
    val spacing = LocalSpacing.current
    CardLayout(
        modifier = Modifier
            .padding(top = spacing.large),
        header = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(
                        id = if (state.showRegister) R.string.register_header else R.string.login_header
                    ),
                    style = MaterialTheme.typography.h2,
                    color = contentColorFor(backgroundColor = MaterialTheme.colors.background),
                )
            }
        },
        footer = {
            if (state.showRegister) {
                Button(
                    onClick = {
                        events(AuthEvents.ResetState)
                        events(AuthEvents.ToggleShowRegister(!state.showRegister))
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
            } else {
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
                        onClick = {
                            events(AuthEvents.ResetState)
                            events(AuthEvents.ToggleShowRegister(!state.showRegister))
                        },
                        text = AnnotatedString(
                            text = stringResource(id = R.string.signup_clickable_text),
                            spanStyle = SpanStyle(
                                color = LightBlue,
                            )
                        ),
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
            }
        }
    ) {
        Column {
            if (state.showRegister) {
                RoundedTextField(
                    value = state.name,
                    placeholder = stringResource(id = R.string.name_placeholder),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    trailingIcon = {
                        if (state.isNameValidated) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colors.primaryVariant
                            )
                        }
                    },
                    onUpdateValue = { name ->
                        events(AuthEvents.UpdateName(name))
                    },
                )
            }
            RoundedTextField(
                value = state.email,
                placeholder = stringResource(id = R.string.email_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
                trailingIcon = {
                    if (state.isEmailValidated) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primaryVariant
                        )
                    }
                },
                onUpdateValue = { email ->
                    events(AuthEvents.UpdateEmail(email))
                },
            )
            RoundedTextField(
                value = state.password,
                visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                placeholder = stringResource(id = R.string.password_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            events(AuthEvents.ToggleShowPassword(!state.showPassword))
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (state.showPassword) R.drawable.ic_visibility_off_24 else R.drawable.ic_visibility_24
                            ),
                            contentDescription = stringResource(
                                id = if (state.showPassword) R.string.hide_password_content_description else R.string.show_password_content_description
                            ),
                            tint = MaterialTheme.colors.muted,
                        )
                    }
                },
                onUpdateValue = { password ->
                    events(AuthEvents.UpdatePassword(password))
                },
                onDone = {
                    // TODO Trigger Login/Register Event
                },
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    // TODO Trigger Login/Register Event
                },
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = stringResource(
                        id = if (state.showRegister) R.string.register_button else R.string.login_button
                    ),
                )
            }
        }
    }
}