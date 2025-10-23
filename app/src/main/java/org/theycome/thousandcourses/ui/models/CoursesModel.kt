package org.theycome.thousandcourses.ui.models

import org.theycome.thousandcourses.network.api.models.Courses

/**
 * Created by Ivan Yakushev on 23.10.2025
 */
sealed interface CoursesModel {
    data class Success(
        val courses: Courses,
    ) : CoursesModel

    object Loading : CoursesModel
}
