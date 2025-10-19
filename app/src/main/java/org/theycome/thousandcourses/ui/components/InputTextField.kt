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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
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
    onInput: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO - add showError flag
    var isEmailValid by remember { mutableStateOf(false) }
    val mask = EmailMask()
    val emailRegex = "\\w+@\\w+[.]{1}\\w+".toRegex()
    var inputText by remember { mutableStateOf(TextFieldValue(value)) }

    TextField(
        value = inputText,
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = {
            inputText = it
            val transformedText = mask.transform(it.text)
            if (emailRegex.matches(transformedText)) {
                onInput(transformedText)
                isEmailValid = true
            } else {
                onInput(null)
                isEmailValid = false
            }
        },
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
        maxLines = 1,
        isError = !isEmailValid, // starndard error underline decoration contradicts figma design
        visualTransformation = mask,
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

private class EmailMask : VisualTransformation {
    private val Char.isAllowed
        get() =
            this in 'a'..'z' ||
                this in 'A'..'Z' ||
                this in '0'..'9' ||
                this == '@' ||
                this == '.'

    override fun filter(text: AnnotatedString): TransformedText =
        text.text.let {
            val transformed = transform(it)
            TransformedText(AnnotatedString(transformed), Mapping(it))
        }

    fun transform(text: String) =
        text.filter {
            it.isAllowed
        }

    private inner class Mapping(
        val original: String,
    ) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val countNotAllowed =
                original
                    .substring(0 until offset)
                    .count { !it.isAllowed }
            return offset - countNotAllowed
        }

        override fun transformedToOriginal(offset: Int): Int {
            val transformed = transform(original)
            var skipCount = 0
            var i = 0
            var j = 0
            while (i in 0 until offset) {
                while (!transformed[j].isAllowed) {
                    j++
                    skipCount++
                }
                i++
                j++
            }

            return offset + skipCount
        }
    }
}
