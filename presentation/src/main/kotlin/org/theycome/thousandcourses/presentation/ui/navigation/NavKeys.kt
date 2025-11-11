package org.theycome.thousandcourses.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import org.theycome.thousandcourses.presentation.R
import org.theycome.thousandcourses.presentation.ui.screens.CoursesAccountScreen
import org.theycome.thousandcourses.presentation.ui.screens.CoursesFavoritesScreen
import org.theycome.thousandcourses.presentation.ui.screens.CoursesMainScreen

/**
 * Created by Ivan Yakushev on 25.10.2025
 */
@Serializable
data object GreetingKey : NavKey

sealed interface CoursesKeyValue {
    @get:StringRes val labelId: Int

    @get:DrawableRes val imageVectorId: Int

    val iconContent: @Composable (() -> Unit)
        get() = {
            Icon(
                painter = painterResource(imageVectorId),
                contentDescription = stringResource(labelId),
            )
        }

    val labelContent: @Composable (() -> Unit)
        get() = {
            Text(stringResource(labelId))
        }
}

@Serializable
data class CoursesKey(
    val value: CoursesKeyValue,
    val content: @Composable ((Modifier) -> Unit),
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

enum class CoursesRoutes(
    val key: CoursesKey,
) {
    MAIN(CoursesKey(CoursesMainKey, { CoursesMainScreen(it) })),
    FAVORITES(CoursesKey(CoursesFavoritesKey, { CoursesFavoritesScreen(it) })),
    ACCOUNT(CoursesKey(CoursesAccountKey, { CoursesAccountScreen(it) })),
}

fun NavBackStack<NavKey>.log() = map { it }.joinToString("\n")
