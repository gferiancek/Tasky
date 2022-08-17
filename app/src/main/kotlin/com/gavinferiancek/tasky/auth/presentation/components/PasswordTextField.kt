package com.gavinferiancek.tasky.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.auth.domain.validation.ValidationState
import com.gavinferiancek.tasky.core.presentation.theme.muted

/**
 * TextField used to display the Email field.
 * @param modifier Modifier applied to the [ValidationTextField]
 * @param password Value to display in [ValidationTextField]
 * @param validationStates List of [ValidationState] objects for this field.
 * @param shouldShowPassword Applies [PasswordVisualTransformation] to value if true.
 * @param shouldDisplayErrors Decides whether or not the [ValidationTextField]'s isError state will be triggered.
 * @param onToggleShowPassword Lambda to toggle [shouldShowPassword] value.
 * @param onUpdatePassword Lambda to update [password] field.
 * @param onDone Lambda that submits entered data. (Equivalent of layout's button press.)
 */
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    validationStates: List<ValidationState>,
    shouldShowPassword: Boolean,
    shouldDisplayErrors: Boolean,
    onToggleShowPassword: (Boolean) -> Unit,
    onUpdatePassword: (String) -> Unit,
    onDone: () -> Unit,
) {
    ValidationTextField(
        modifier = modifier.fillMaxWidth(),
        value = password,
        validationStates = validationStates,
        shouldDisplayErrors = shouldDisplayErrors,
        visualTransformation = if (shouldShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = stringResource(id = R.string.password_placeholder),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    onToggleShowPassword(!shouldShowPassword)
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (shouldShowPassword) R.drawable.ic_visibility_off_24 else R.drawable.ic_visibility_24
                    ),
                    contentDescription = stringResource(
                        id = if (shouldShowPassword) R.string.hide_password_content_description else R.string.show_password_content_description
                    ),
                    tint = MaterialTheme.colors.muted,
                )
            }
        },
        onUpdateValue = { newPassword ->
            onUpdatePassword(newPassword)
        },
        onDone = onDone,
    )
}