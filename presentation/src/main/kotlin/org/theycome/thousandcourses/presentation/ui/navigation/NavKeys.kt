package org.theycome.thousandcourses.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import org.theycome.thousandcourses.presentation.R

/**
 * Created by Ivan Yakushev on 25.10.2025
 */
@Serializable
data object GreetingKey : NavKey

@Serializable
data object TryKey : NavKey

@Serializable
sealed interface CoursesKeyValue {
    @get:StringRes val labelId: Int

    @get:DrawableRes val imageVectorId: Int

    val content: @Composable ((Modifier) -> Unit)
        get() = { modifier ->
            IconButton(
                onClick = {},
                modifier = modifier,
            ) {
                Icon(
                    painter = painterResource(imageVectorId),
                    contentDescription = stringResource(labelId),
                )
            }
        }

//            IconButton(
//                onClick = {},
//                modifier =
//                    Modifier
//                        // .requiredHeight(200.dp)
//                        .weight(1.0f / CoursesRoutes.entries.size),
//            ) {
//                entry.iconContent()
//
// //                Icon(
// //                    painter = painterResource(entry.imageVectorId),
// //                    contentDescription = stringResource(entry.labelId),
// //                )
//
//                Icon(
//                    painter = painterResource(id = R.drawable.vk),
//                    contentDescription = null,
//                    // modifier = Modifier.fillMaxSize(),
//                    //                        Modifier
//                    //                            .requiredHeight(200.dp),
//                )
//            }

//            Icon(
//                painter = painterResource(id = R.drawable.vk),
//                contentDescription = null,
//                modifier =
//                    Modifier
//                        .width(50.dp)
//                        .height(40.dp),
//            )
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
