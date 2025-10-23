package org.theycome.thousandcourses.presentation.ui.components.validators

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Created by Ivan Yakushev on 19.10.2025
 */
class EmailValidator :
    VisualTransformation,
    TextValidator {
    private val Char.isAllowed
        get() =
            this in 'a'..'z' ||
                this in 'A'..'Z' ||
                this in '0'..'9' ||
                this == '@' ||
                this == '.'

    private val regex = "\\w+@\\w+[.]{1}\\w+".toRegex()

    override fun filter(text: AnnotatedString): TransformedText =
        text.text.let {
            val transformed = transform(it)
            TransformedText(
                AnnotatedString(transformed),
                Mapping(it),
            )
        }

    override fun transform(input: String): String =
        input.filter {
            it.isAllowed
        }

    override fun validate(input: String): Boolean = regex.matches(transform(input))

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
