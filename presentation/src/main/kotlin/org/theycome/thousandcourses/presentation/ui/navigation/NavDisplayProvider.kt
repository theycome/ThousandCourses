package org.theycome.thousandcourses.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import org.theycome.thousandcourses.presentation.ui.screens.CoursesScreen
import org.theycome.thousandcourses.presentation.ui.screens.GreetingScreen

/**
 * Created by Ivan Yakushev on 31.10.2025
 */
@Composable
fun NavDisplayProvider(
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier.Companion,
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider =
            entryProvider {
                entry<GreetingKey> { GreetingScreen(backStack, modifier) }
                entry<CoursesKey> { key ->
                    CoursesScreen(
                        backStack = backStack,
                        coursesKey = key,
                        modifier = modifier,
                    )
                }
            },
    )
}
