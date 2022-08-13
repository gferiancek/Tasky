package com.gavinferiancek.tasky.auth.presentation.login

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
fun LoginScreen(
    state: LoginState,
    events: (LoginEvents) -> Unit,
    onNavigateToRegister: () -> Unit,
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
                    text = stringResource(id = R.string.login_header),
                    style = MaterialTheme.typography.h2,
                    color = contentColorFor(backgroundColor = MaterialTheme.colors.background),
                )
            }
        },
        footer = {
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
                        onNavigateToRegister()
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
    ) {
        Column {
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
                    events(LoginEvents.UpdateEmail(email))
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
                            events(LoginEvents.ToggleShowPassword(!state.showPassword))
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
                    events(LoginEvents.UpdatePassword(password))
                },
                onDone = {
                    // TODO Trigger Login Event
                },
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    // TODO Trigger Login Event
                },
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = stringResource(id = R.string.login_button),
                )
            }
        }
    }
}