package org.theycome.thousandcourses.presentation.core

import arrow.core.left
import arrow.core.raise.either
import arrow.core.right
import io.kotest.core.spec.style.DescribeSpec
import io.kotlintest.matchers.shouldBe
import org.theycome.thousandcourses.presentation.ui.components.validators.EmailValidator
import org.theycome.thousandcourses.presentation.ui.components.validators.TextValidator

/**
 * Created by Ivan Yakushev on 04.02.2026
 */
class EmailValidatorTest :
    DescribeSpec({
        val error = TextValidator.Error.left()
        val validator = EmailValidator()

        describe("proper pruning") {
            it("cyrillic symbols") {
                either {
                    validator.pruneAndThenValidate("rйвuаазд@ввmдлоаыаail.rвввu")
                } shouldBe "ru@mail.ru".right()
            }
            it("special symbols") {
                either {
                    validator.pruneAndThenValidate("r(u@ma&*i-l.ru")
                } shouldBe "ru@mail.ru".right()
            }
        }

        describe("validation raises Error") {
            it("no @") {
                either { validator.pruneAndThenValidate("fff.ru") } shouldBe error
                either { validator.pruneAndThenValidate("fff.ru") } shouldBe error
                either { validator.pruneAndThenValidate("dd9") } shouldBe error
            }
            it("multiple @") {
                either { validator.pruneAndThenValidate("@ff@f.ru") } shouldBe error
            }
            it("no .") {
                either { validator.pruneAndThenValidate("@fffru") } shouldBe error
                either { validator.pruneAndThenValidate("dd@fff") } shouldBe error
            }
            it("multiple .") {
                either { validator.pruneAndThenValidate("@f.ff.ru") } shouldBe error
            }
            it(". before @") {
                either { validator.pruneAndThenValidate("fff.@3.ru") } shouldBe error
            }
        }
    })
