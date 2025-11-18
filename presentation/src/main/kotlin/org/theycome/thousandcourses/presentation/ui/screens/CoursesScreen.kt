package org.theycome.thousandcourses.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.theycome.thousandcourses.presentation.R
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
            CoursesMainKey -> CoursesMainScreen(modifier)
            CoursesFavoritesKey -> CoursesFavoritesScreen(modifier)
            CoursesAccountKey -> CoursesAccountScreen(modifier)
        }
        NavigationBar(modifier)
    }

    //    NavigationSuiteScaffold(
    //        modifier = modifier,
    //        navigationSuiteItems = {
    //            CoursesRoutes.entries.forEach { route ->
    //                val value = route.key.value
    //                item(
    //                    selected = coursesKey == route.key,
    //                    onClick = {
    //                        if (backStack.last() != route.key) {
    //                            backStack.add(route.key)
    //                        }
    //                    },
    //                    icon = value.iconContent,
    //                    label = value.labelContent,
    //                    alwaysShowLabel = false,
    //                )
    //            }
    //        },
    //    ) {
    //        when (coursesKey.value) {
    //            CoursesMainKey -> CoursesMainScreen(modifier)
    //            CoursesFavoritesKey -> CoursesFavoritesScreen(modifier)
    //            CoursesAccountKey -> CoursesAccountScreen(modifier)
    //        }
    //    }
}

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    Row(modifier) {
        CoursesRoutes.entries.forEach { route ->
            val value = route.key.value
            Icon(
                painter = painterResource(id = R.drawable.vk),
                contentDescription = null,
                modifier =
                    Modifier
                        .width(50.dp)
                        .height(40.dp),
            )
        }
    }
}
