package org.theycome.thousandcourses.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import org.theycome.thousandcourses.presentation.R
import org.theycome.thousandcourses.presentation.ui.screens.coursesKeyBottomNavContent

/**
 * Created by Ivan Yakushev on 25.10.2025
 */
@Serializable
data object GreetingKey : NavKey

@Serializable
sealed interface CoursesKeyValue {
    @get:StringRes val labelId: Int

    @get:DrawableRes val imageVectorId: Int

    val content: @Composable CoursesKeyValue.(Modifier) -> Unit
        get() = coursesKeyBottomNavContent
}

@Serializable
data class CoursesKey(
    val value: CoursesKeyValue,
) : NavKey

@Serializable
data object CoursesMainKey : CoursesKeyValue {
    override val labelId: Int
        get() = R.string.main_tab
    override val imageVectorId: Int
        get() = R.drawable.main
}

@Serializable
data object CoursesFavoritesKey : CoursesKeyValue {
    override val labelId: Int
        get() = R.string.favorites_tab
    override val imageVectorId: Int
        get() = R.drawable.favorites
}

@Serializable
data object CoursesAccountKey : CoursesKeyValue {
    override val labelId: Int
        get() = R.string.account_tab
    override val imageVectorId: Int
        get() = R.drawable.account
}

val mainCoursesKey = CoursesKey(CoursesMainKey)
val favoritesCoursesKey = CoursesKey(CoursesFavoritesKey)
val accountCoursesKey = CoursesKey(CoursesAccountKey)

enum class CoursesRoutes(
    val key: CoursesKey,
) {
    Main(mainCoursesKey),
    Favorites(favoritesCoursesKey),
    Account(accountCoursesKey),
}

fun NavBackStack<NavKey>.log() =
    println(
        "=== back stack ===\n" +
            map { it }.joinToString("\n") +
            "\n===\n",
    )
