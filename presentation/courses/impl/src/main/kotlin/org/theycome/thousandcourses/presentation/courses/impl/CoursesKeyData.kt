package org.theycome.thousandcourses.presentation.courses.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.theycome.thousandcourses.presentation.core.R
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey
import org.theycome.thousandcourses.presentation.courses.impl.ui.screens.coursesKeyBottomNavContent

/**
 * Created by Ivan Yakushev on 28.12.2025
 */
sealed interface CoursesKeyData {
    val labelId: Int
    val imageVectorId: Int
    val content: @Composable CoursesKeyData.(Modifier) -> Unit
        get() = coursesKeyBottomNavContent

    data object Main : CoursesKeyData {
        override val labelId: Int
            get() = R.string.main_tab
        override val imageVectorId: Int
            get() = R.drawable.main
    }

    data object Favorites : CoursesKeyData {
        override val labelId: Int
            get() = R.string.favorites_tab
        override val imageVectorId: Int
            get() = R.drawable.favorites
    }

    data object Account : CoursesKeyData {
        override val labelId: Int
            get() = R.string.account_tab
        override val imageVectorId: Int
            get() = R.drawable.account
    }

    companion object {
        fun of(key: CoursesKey): CoursesKeyData =
            when (key) {
                is CoursesKey.Main -> Main
                is CoursesKey.Account -> Account
                is CoursesKey.Favorites -> Favorites
            }
    }
}

enum class CoursesRoutes(
    val key: CoursesKeyData,
) {
    Main(CoursesKeyData.Main),
    Favorites(CoursesKeyData.Favorites),
    Account(CoursesKeyData.Account),
}
