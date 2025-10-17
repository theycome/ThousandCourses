package org.theycome.thousandcourses

import io.kotest.core.spec.style.DescribeSpec
import io.kotlintest.matchers.shouldBe

/**
 * Created by Ivan Yakushev on 17.10.2025
 */
class RegexTest :
    DescribeSpec({
        describe("regex") {
            val emailRegex = "\\w+@\\w+[.]{1}\\w+".toRegex()

            describe("valid") {
                it("valid") {
                    emailRegex.matches("dd@fff.ru") shouldBe true
                    emailRegex.matches("q@65464.5") shouldBe true
                    emailRegex.matches("55@f.8u") shouldBe true
                }
            }

            describe("invalid") {
                it("no @") {
                    emailRegex.matches("fff.ru") shouldBe false
                    emailRegex.matches("dd9") shouldBe false
                }
                it("multiple @") {
                    emailRegex.matches("@ff@f.ru") shouldBe false
                }
                it("no .") {
                    emailRegex.matches("@fffru") shouldBe false
                    emailRegex.matches("dd@fff") shouldBe false
                }
                it("multiple .") {
                    emailRegex.matches("@f.ff.ru") shouldBe false
                }
                it(". before @") {
                    emailRegex.matches("fff.@3.ru") shouldBe false
                }
                it("illegal characters") {
                    emailRegex.matches("ru)@mail.ru") shouldBe false
                }
            }
        }
    })
