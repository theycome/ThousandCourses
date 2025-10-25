package org.theycome.thousandcourses.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import org.theycome.thousandcourses.presentation.R

/**
 * Created by Ivan Yakushev on 25.10.2025
 */
@Serializable
data object Main : NavKey

@Serializable
data object Favorites : NavKey

@Serializable
data object Account : NavKey

enum class TopLevelDestinations(
    val navKey: NavKey,
    @param:StringRes val labelId: Int,
    @param:DrawableRes val imageVectorId: Int,
) {
    MAIN(Main, R.string.main_tab, R.drawable.main),
    FAVORITES(Favorites, R.string.favorites_tab, R.drawable.favorites),
    ACCOUNT(Account, R.string.account_tab, R.drawable.account),
}
