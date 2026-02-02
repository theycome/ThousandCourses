package org.theycome.thousandcourses.presentation.courses.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.theycome.thousandcourses.navigator.Navigator
import org.theycome.thousandcourses.presentation.core.R
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey.Account
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey.Favorites
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey.Main
import org.theycome.thousandcourses.presentation.courses.impl.ui.screens.CoursesAccountScreen
import org.theycome.thousandcourses.presentation.courses.impl.ui.screens.CoursesFavoritesScreen
import org.theycome.thousandcourses.presentation.courses.impl.ui.screens.CoursesMainScreen
import org.theycome.thousandcourses.presentation.courses.impl.ui.screens.coursesKeyBottomNavContent

/**
 * Created by Ivan Yakushev on 28.12.2025
 */
sealed interface CoursesKeyData {
    val labelId: Int
    val imageVectorId: Int
    val mainContent: @Composable (Modifier) -> Unit
    val bottomBarContent: @Composable CoursesKeyData.(selected: Boolean, action: () -> Unit, Modifier) -> Unit
        get() = coursesKeyBottomNavContent

    data object Main : CoursesKeyData {
        override val labelId: Int
            get() = R.string.main_tab
        override val imageVectorId: Int
            get() = R.drawable.main
        override val mainContent: @Composable ((Modifier) -> Unit)
            get() = {
                CoursesMainScreen(it)
            }
    }

    data object Favorites : CoursesKeyData {
        override val labelId: Int
            get() = R.string.favorites_tab
        override val imageVectorId: Int
            get() = R.drawable.favorites
        override val mainContent: @Composable ((Modifier) -> Unit)
            get() = {
                CoursesFavoritesScreen(it)
            }
    }

    data object Account : CoursesKeyData {
        override val labelId: Int
            get() = R.string.account_tab
        override val imageVectorId: Int
            get() = R.drawable.account
        override val mainContent: @Composable ((Modifier) -> Unit)
            get() = {
                CoursesAccountScreen(it)
            }
    }

    companion object {
        /**
         * A conversion from key abstraction in courses:api to courses:impl
         */
        fun of(key: CoursesKey): CoursesKeyData =
            when (key) {
                is CoursesKey.Main -> Main
                is CoursesKey.Account -> Account
                is CoursesKey.Favorites -> Favorites
            }
    }
}

data class CoursesRoute(
    val keyData: CoursesKeyData,
    val selected: Boolean,
    val action: () -> Unit,
) {
    companion object {
        fun routesOf(
            selectedKey: CoursesKey,
            navigator: Navigator?,
        ): List<CoursesRoute> =
            emptyActions.map { (key, emptyAction) ->
                val selected = selectedKey == key
                val action =
                    navigator?.let {
                        if (selected) {
                            emptyAction
                        } else {
                            { navigator.goTo(key) }
                        }
                    } ?: emptyAction

                CoursesRoute(
                    keyData = CoursesKeyData.of(key),
                    selected = selected,
                    action = action,
                )
            }

        private val emptyActions: Map<CoursesKey, () -> Unit> =
            listOf(
                Main,
                Account,
                Favorites,
            ).associateWith { { } }
    }
}
