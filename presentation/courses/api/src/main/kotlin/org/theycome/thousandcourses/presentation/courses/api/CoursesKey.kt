package org.theycome.thousandcourses.presentation.courses.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Created by Ivan Yakushev on 28.12.2025
 */
@Serializable
sealed interface CoursesKey : NavKey {
    data object Main : CoursesKey

    data object Favorites : CoursesKey

    data object Account : CoursesKey
}
