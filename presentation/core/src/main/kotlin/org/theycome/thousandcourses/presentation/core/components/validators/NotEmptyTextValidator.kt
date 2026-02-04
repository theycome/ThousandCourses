package org.theycome.thousandcourses.presentation.ui.components.validators

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Created by Ivan Yakushev on 19.10.2025
 */
class NotEmptyTextValidator :
    VisualTransformation,
    TextValidator {
    override fun filter(text: AnnotatedString): TransformedText =
        TransformedText(
            AnnotatedString(text.text),
            Mapping(),
        )

    override fun prune(input: String): String = input

    override fun validate(input: String): Boolean = input.isNotEmpty()

    private inner class Mapping : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int = offset

        override fun transformedToOriginal(offset: Int): Int = offset
    }
}
