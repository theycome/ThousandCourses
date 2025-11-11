package org.theycome.thousandcourses.presentation.ui.navigation

import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

/**
 * Created by Ivan Yakushev on 25.10.2025
 */
@Composable
fun CoursesScreenScaffold(
    backStack: NavBackStack<NavKey>,
    coursesKey: CoursesKey,
    modifier: Modifier = Modifier,
) {
    NavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            CoursesRoutes.entries.forEach { route ->
                val value = route.key.value
                item(
                    selected = coursesKey == route.key,
                    onClick = {
                        if (backStack.last() != route.key) {
                            backStack.add(route.key)
                        }
                    },
                    icon = value.iconContent,
                    label = value.labelContent,
                    alwaysShowLabel = false,
                )
            }
        },
    ) {
        coursesKey.content(modifier)
    }
}
