package org.theycome.thousandcourses.presentation.ui.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue
import kotlin.math.min
import kotlin.text.forEach

/**
 * Created by Ivan Yakushev on 16.10.2025
 *
 * e.g. "+7##-##"
 *
 * ```
 *     val mask =
 *         CustomMask.MASK_SYMBOL.let {
 *             CustomMask("+7$it$it-$it$it")
 *         }
 *
 *     TextField(
 *         visualTransformation = mask,
 * ```
 */
class CustomMask(
    private val mask: String,
) : VisualTransformation {
    private val specialSymbolsIndices = mask.indices.filter { mask[it] != MASK_SYMBOL }

    /**
     * Add mask character(s) up-to to current position OR current character of text
     * The output is a string with only symbols in special positions being user-entered
     */
    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            if (maskIndex < mask.length) {
                out += char
                maskIndex++
            }
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val offsetValue = offset.absoluteValue
                if (offsetValue == 0) return 0

                var numberOfHashtags = 0
                val masked =
                    mask.takeWhile {
                        if (it == MASK_SYMBOL) numberOfHashtags++
                        numberOfHashtags < offsetValue
                    }
                return min(masked.length + 1, mask.length)
            }

            override fun transformedToOriginal(offset: Int): Int =
                mask.take(offset.absoluteValue).count {
                    it ==
                        MASK_SYMBOL
                }
        }

    companion object {
        const val MASK_SYMBOL = '#'
    }
}
