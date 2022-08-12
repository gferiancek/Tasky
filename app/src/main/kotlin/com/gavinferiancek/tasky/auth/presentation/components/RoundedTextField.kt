package com.gavinferiancek.tasky.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.VisualTransformation
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.gavinferiancek.tasky.core.presentation.theme.muted

/**
 * Basic TextField with custom styling extracted into Composable function.
 * @param value Input text to be shown in the TextField.
 * @param visualTransformation Applies VisualTransformation to [value] field. Defaults to
 * VisualTransformation.None.
 * @param placeholder String displayed when TextField is empty.
 * @param keyboardOptions software keyboard options that contains configuration such as KeyboardType and ImeAction.
 * @param trailingIcon Trailing icon to be displayed at the end of the text field container.
 * @param onUpdateValue Lambda that takes a string to pass back to VM for state management.
 * @param onDone Lambda that submits entered data. (Equivalent of layout's button press.)
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoundedTextField(
    modifier: Modifier = Modifier,
    value: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    trailingIcon: @Composable () -> Unit,
    onUpdateValue: (String) -> Unit,
    onDone: () -> Unit = {},
) {
    val spacing = LocalSpacing.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = spacing.medium,
            ),
        value = value,
        onValueChange = { newValue ->
            onUpdateValue(newValue)
        },
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colors.muted,
            )
        },
        visualTransformation = visualTransformation,
        singleLine = true,
        trailingIcon = { trailingIcon() },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}