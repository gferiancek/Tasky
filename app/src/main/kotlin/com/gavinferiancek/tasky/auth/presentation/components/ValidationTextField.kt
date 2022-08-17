package com.gavinferiancek.tasky.auth.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.VisualTransformation
import com.gavinferiancek.tasky.auth.domain.validation.ValidationState
import com.gavinferiancek.tasky.core.presentation.theme.LocalSpacing
import com.gavinferiancek.tasky.core.presentation.theme.muted

/**
 * OutlinedTextField with custom styling/error handling applied.
 * @param modifier Modifier applied to OutlinedTextField.
 * @param value Input text to be shown in the TextField.
 * @param validationStates List of [ValidationState] objects used for styling and error message display.
 * @param shouldDisplayErrors Decides whether or not the [OutlinedTextField]'s isError state will be triggered.
 * @param visualTransformation Applies [VisualTransformation] to [value] field. Defaults to
 * VisualTransformation.None.
 * @param placeholder String displayed when TextField is empty.
 * @param keyboardOptions software keyboard options that contains configuration such as KeyboardType and ImeAction.
 * @param trailingIcon Trailing icon to be displayed at the end of the text field container.
 * @param onUpdateValue Lambda that updates value field.
 * @param onDone Lambda that submits entered data. (Equivalent of layout's button press.)
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ValidationTextField(
    modifier: Modifier = Modifier,
    value: String,
    validationStates: List<ValidationState>,
    shouldDisplayErrors: Boolean,
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

    val hasErrors = remember(validationStates) {
        validationStates.any { !it.isValid }
    }
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = { newValue ->
                onUpdateValue(newValue)
            },
            placeholder = { Text(text = placeholder) },
            isError = shouldDisplayErrors,
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
                focusedIndicatorColor = MaterialTheme.colors.muted,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = if (hasErrors) MaterialTheme.colors.error else MaterialTheme.colors.muted,
                placeholderColor = MaterialTheme.colors.muted,
            ),
        )
        Column(
            modifier = Modifier
                .padding(
                    start = spacing.small,
                    bottom = spacing.medium,
                )
        ) {
            AnimatedVisibility(visible = shouldDisplayErrors && hasErrors) {
                Column {
                    validationStates.forEach { validation ->
                        val isValid = remember(validation) {
                            validation.isValid
                        }
                        Text(
                            text = validation.message?.asString() ?: "",
                            color = if (isValid) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }
            }
        }
    }
}