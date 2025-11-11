package org.theycome.thousandcourses.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.rememberNavBackStack
import org.theycome.thousandcourses.presentation.ui.navigation.GreetingKey
import org.theycome.thousandcourses.presentation.ui.navigation.NavDisplayProvider
import org.theycome.thousandcourses.presentation.ui.theme.ThousandCoursesTheme

/**
 * Created by Ivan Yakushev on 11.11.2025
 */
val mainContent: @Composable () -> Unit = {
    val backStack = rememberNavBackStack(GreetingKey)

    ThousandCoursesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavDisplayProvider(
                backStack = backStack,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
