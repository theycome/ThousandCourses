package org.theycome.thousandcourses.presentation.courses.impl.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            model = CoursesModel.default(),
        )
    }

@Composable
fun CoursesScreenStateful(
    key: CoursesKey,
    modifier: Modifier = Modifier,
    viewModel: CoursesViewModel = hiltViewModel(),
) {
    val model: CoursesModel by viewModel.coursesFlow.flow.collectAsStateWithLifecycle()
    val loadingError: NetworkDatasource.LoadingError? by viewModel.loadingCoursesErrorFlow.flow
        .collectAsStateWithLifecycle()

    CoursesScreen(
        keyData = CoursesKeyData.of(key),
        model = model,
        modifier = modifier,
        error = loadingError,
    )
}

@Composable
fun CoursesScreen(
    keyData: CoursesKeyData,
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
        NavigationBar(keyData, modifier)
    }
}

@Preview
@Composable
fun NavigationBarPreview() {
    ThousandCoursesTheme {
        NavigationBar(CoursesKeyData.Main)
    }
}

@Composable
fun NavigationBar(
    keyData: CoursesKeyData,
    modifier: Modifier = Modifier,
) {
    val routes = CoursesRoute.routesOf(keyData)
    Row(
        modifier =
            modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val entryModifier =
            Modifier
                .weight(1.0f / routes.size)
                .fillMaxHeight()

        routes.forEach { route ->
            with(route.keyData) {
                bottomBarContent(route.selected, entryModifier)
            }
        }
    }
}
