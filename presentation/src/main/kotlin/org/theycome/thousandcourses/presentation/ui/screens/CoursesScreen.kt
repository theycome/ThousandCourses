package org.theycome.thousandcourses.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.theycome.thousandcourses.network.api.NetworkDatasource
import org.theycome.thousandcourses.presentation.ui.models.CoursesModel
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
            model = CoursesModel.default(),
        )
    }

@Composable
fun CoursesScreenStateful(
    coursesKey: CoursesKey,
    modifier: Modifier = Modifier,
    viewModel: CoursesViewModel = hiltViewModel(),
) {
    val model: CoursesModel by viewModel.coursesFlow.flow.collectAsStateWithLifecycle()
    val loadingError: NetworkDatasource.LoadingError? by viewModel.loadingCoursesErrorFlow.flow
        .collectAsStateWithLifecycle()

    CoursesScreen(
        coursesKey = coursesKey,
        model = model,
        modifier = modifier,
        error = loadingError,
    )
}

@Composable
fun CoursesScreen(
    coursesKey: CoursesKey,
    model: CoursesModel,
    modifier: Modifier = Modifier,
    error: NetworkDatasource.LoadingError? = null,
) {
    when (model) {
        is CoursesModel.Loading -> {
            println("Loading...")
        }
        is CoursesModel.Success -> {
            println(model.courses.courses.size)
        }
    }
    error?.let(::println)

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
