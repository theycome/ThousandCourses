package org.theycome.thousandcourses.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesAccountKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesFavoritesKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesMainKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesRoutes
import org.theycome.thousandcourses.presentation.ui.navigation.mainCoursesKey
import org.theycome.thousandcourses.presentation.ui.theme.ThousandCoursesTheme

/**
 * Created by Ivan Yakushev on 25.10.2025
 */

@Preview
@Composable
fun CoursesScreenPreview() =
    ThousandCoursesTheme {
        CoursesScreen(
            coursesKey = mainCoursesKey,
        )
    }

@Composable
fun CoursesScreen(
    coursesKey: CoursesKey,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        when (coursesKey.value) {
            is CoursesMainKey -> {
                CoursesMainScreen(modifier)
            }
            is CoursesFavoritesKey -> {
                CoursesFavoritesScreen(modifier)
            }
            is CoursesAccountKey -> {
                CoursesAccountScreen(modifier)
            }
        }
        NavigationBar(modifier)
    }
}

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color.DarkGray),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val entryModifier =
            Modifier
                .weight(1.0f / CoursesRoutes.entries.size)

        CoursesRoutes.entries.forEach { route ->
            route.key.value.content(entryModifier)
        }
    }
}
