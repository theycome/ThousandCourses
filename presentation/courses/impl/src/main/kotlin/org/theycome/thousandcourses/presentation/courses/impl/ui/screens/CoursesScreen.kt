package org.theycome.thousandcourses.presentation.courses.impl.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.theycome.thousandcourses.network.api.NetworkDatasource
import org.theycome.thousandcourses.presentation.core.theme.ThousandCoursesTheme
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey
import org.theycome.thousandcourses.presentation.courses.api.models.CoursesModel
import org.theycome.thousandcourses.presentation.courses.impl.CoursesKeyData
import org.theycome.thousandcourses.presentation.courses.impl.CoursesRoute
import org.theycome.thousandcourses.presentation.courses.impl.viewmodels.CoursesViewModel

/**
 * Created by Ivan Yakushev on 25.10.2025
 */
@Preview
@Composable
fun CoursesScreenPreview() =
    ThousandCoursesTheme {
        CoursesScreen(
            keyData = CoursesKeyData.Main,
            routes = CoursesRoute.routesOf(CoursesKey.Main, null),
            model = CoursesModel.default(),
        )
    }

@Composable
fun CoursesScreenStateful(
    keyData: CoursesKeyData,
    routes: List<CoursesRoute>,
    modifier: Modifier = Modifier,
    viewModel: CoursesViewModel = hiltViewModel(),
) {
    val model: CoursesModel by viewModel.coursesFlow.flow.collectAsStateWithLifecycle()
    val loadingError: NetworkDatasource.LoadingError? by viewModel.loadingCoursesErrorFlow.flow
        .collectAsStateWithLifecycle()

    CoursesScreen(
        keyData = keyData,
        routes = routes,
        model = model,
        modifier = modifier,
        error = loadingError,
    )
}

@Composable
fun CoursesScreen(
    keyData: CoursesKeyData,
    routes: List<CoursesRoute>,
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
        keyData.mainContent(modifier)
        NavigationBar(
            keyData = keyData,
            routes = routes,
            modifier = modifier,
        )
    }
}
