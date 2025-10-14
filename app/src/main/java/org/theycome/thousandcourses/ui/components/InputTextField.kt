package org.theycome.thousandcourses.ui.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.theycome.thousandcourses.R
import org.theycome.thousandcourses.ui.theme.ThousandCoursesTheme

/**
 * Created by Ivan Yakushev on 13.10.2025
 */
@Composable
fun InputTextField(
    value: String,
    @StringRes placeholderId: Int,
    modifier: Modifier = Modifier,
) {
    var inputText by remember { mutableStateOf(TextFieldValue(value)) }

    TextField(
        value = inputText,
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = { inputText = it },
        placeholder = {
            Text(stringResource(placeholderId))
        },
        shape = RoundedCornerShape(30.dp),
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
    )
}

@Composable
@Preview
fun InputTextFieldPreview() {
    ThousandCoursesTheme {
        InputTextField("", R.string.enter_caption)
    }
}
