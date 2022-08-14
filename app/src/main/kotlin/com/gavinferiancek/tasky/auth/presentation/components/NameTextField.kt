package com.gavinferiancek.tasky.auth.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.gavinferiancek.tasky.R
import com.gavinferiancek.tasky.auth.domain.validation.ValidationState

/**
 * TextField used to display the Name field.
 * @param modifier Modifier applied to the [ValidationTextField].
 * @param name Value to display in [ValidationTextField].
 * @param validationStates List of [ValidationState] objects for this field.
 * @param displayErrors Decides whether or not the [ValidationTextField]'s isError state will be triggered.
 * @param onUpdateName Lambda to update [name] field.
 */
@Composable
fun NameTextField(
    modifier: Modifier = Modifier,
    name: String,
    validationStates: List<ValidationState>,
    displayErrors: Boolean,
    onUpdateName: (String) -> Unit,
) {
    ValidationTextField(
        modifier = modifier.fillMaxWidth(),
        value = name,
        validationStates = validationStates,
        isError = displayErrors,
        placeholder = stringResource(id = R.string.name_placeholder),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
        ),
        trailingIcon = {
            if (validationStates.all { it.isValidated }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        },
        onUpdateValue = onUpdateName,
    )
}