package org.theycome.thousandcourses.presentation.ui.components.validators

import arrow.core.raise.Raise
import arrow.core.raise.ensure

/**
 * Created by Ivan Yakushev on 19.10.2025
 */
interface TextValidator {
    data object Error

    fun transform(input: String): String

    fun validate(input: String): Boolean

    /**
     * Use Strategy pattern
     */
    context(raise: Raise<Error>)
    fun transformAndThenValidate(input: String): String {
        val transformed = transform(input)
        raise.ensure(validate(transformed)) {
            Error
        }
        return transformed
    }
}
