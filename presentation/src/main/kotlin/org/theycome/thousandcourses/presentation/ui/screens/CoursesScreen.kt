package org.theycome.thousandcourses.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesAccountKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesFavoritesKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesMainKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesRoutes
import org.theycome.thousandcourses.presentation.ui.navigation.mainCoursesKey
import org.theycome.thousandcourses.presentation.ui.theme.ThousandCoursesTheme
import org.theycome.thousandcourses.presentation.viewmodels.CoursesViewModel

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
fun CoursesScreenStateful(
    coursesKey: CoursesKey,
    modifier: Modifier = Modifier,
    viewModel: CoursesViewModel = hiltViewModel(),
) {
    viewModel.loadCourses()

//    val kodeModel by viewmodel.kodeModelFlow.flow.collectAsStateWithLifecycle()
//    val loadingError by viewmodel.loadingErrorFlow.flow.collectAsStateWithLifecycle()

    CoursesScreen(coursesKey, modifier)
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

@Preview
@Composable
fun NavigationBarPreview() {
    ThousandCoursesTheme {
        NavigationBar()
    }
}

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val entryModifier =
            Modifier
                .weight(1.0f / CoursesRoutes.entries.size)
                .fillMaxHeight()

        CoursesRoutes.entries.forEach { route ->
            with(route.key.value) {
                content(entryModifier)
            }
        }
    }
}
