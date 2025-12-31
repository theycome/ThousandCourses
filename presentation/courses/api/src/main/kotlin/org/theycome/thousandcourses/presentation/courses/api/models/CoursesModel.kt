package org.theycome.thousandcourses.presentation.courses.api.models

import org.theycome.thousandcourses.network.api.models.Course
import org.theycome.thousandcourses.network.api.models.Courses

/**
 * Created by Ivan Yakushev on 23.10.2025
 */
sealed interface CoursesModel {
    data class Success(
        val courses: Courses,
    ) : CoursesModel

    object Loading : CoursesModel

    companion object {
        fun default(): Success =
            Success(
                Courses(
                    listOf(
                        Course(
                            hasLike = false,
                            id = 100,
                            price = "999",
                            publishDate = "2024-02-02",
                            rate = "4.9",
                            startDate = "2024-05-22",
                            text =
                                "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven," +
                                    " работу с базами данных и API. Создайте свой собственный проект, " +
                                    "собрав портфолио и став востребованным специалистом для любой IT компании.",
                            title = "Java-разработчик с нуля",
                        ),
                        Course(
                            hasLike = true,
                            id = 101,
                            price = "12 000",
                            publishDate = "2024-01-20",
                            rate = "3.9",
                            startDate = "2024-09-10",
                            text =
                                "Освой профессию 3D-дженералиста и стань универсальным специалистом, " +
                                    "который умеет создавать 3D-модели, текстуры и анимации, " +
                                    "а также может строить карьеру в геймдеве, кино, рекламе или дизайне.",
                            title = "3D-дженералист",
                        ),
                    ),
                ),
            )
    }
}
