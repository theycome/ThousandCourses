package org.theycome.thousandcourses.presentation.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import arrow.core.raise.either
import org.theycome.thousandcourses.presentation.core.R
import org.theycome.thousandcourses.presentation.core.theme.ThousandCoursesTheme
import org.theycome.thousandcourses.presentation.ui.components.validators.TextValidator

/**
 * Created by Ivan Yakushev on 13.10.2025
 */
@Composable
fun InputTextField(
    value: String,
    @StringRes placeholderId: Int,
    onInput: (String?) -> Unit,
    modifier: Modifier = Modifier,
    validator: TextValidator? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    showError: Boolean = false,
) {
    var isValid by remember {
        mutableStateOf(validator?.let { false } ?: true)
    }
    var inputText by remember { mutableStateOf(TextFieldValue(value)) }

    TextField(
        value = inputText,
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = {
            inputText = it
            if (validator == null) {
                onInput(it.text)
                isValid = true
            } else {
                either {
                    validator.transformAndThenValidate(it.text)
                }.onRight { result ->
                    onInput(result)
                    isValid = true
                }.onLeft {
                    onInput(null)
                    isValid = false
                }
            }
        },
        placeholder = { Text(stringResource(placeholderId)) },
        shape = RoundedCornerShape(30.dp),
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
        maxLines = 1,
        isError = if (showError) !isValid else false, // standard error underline decoration contradicts figma design
        visualTransformation = visualTransformation,
    )
}

@Composable
@Preview
fun InputTextFieldPreview() {
    ThousandCoursesTheme {
        InputTextField(
            value = "",
            placeholderId = R.string.enter_caption,
            onInput = {},
        )
    }
}
