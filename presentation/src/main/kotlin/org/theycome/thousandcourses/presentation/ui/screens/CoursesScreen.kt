package org.theycome.thousandcourses.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.IconButton
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

// @Composable
// fun CoursesScreenPre(
//    //coursesKey: CoursesKey,
//    coursesKey: CoursesKeyValue,
//    modifier: Modifier = Modifier,
// ) {
//    Column(modifier) {
//        if (coursesKey.value is CoursesMainKey) {
//            CoursesMainScreen(modifier)
//        } else if (coursesKey.value is CoursesFavoritesKey) {
//            CoursesFavoritesScreen(modifier)
//        } else if (coursesKey.value is CoursesAccountKey) {
//            CoursesAccountScreen(modifier)
//        }
//        NavigationBar(modifier)
//    }
//
//    //    NavigationSuiteScaffold(
//    //        modifier = modifier,
//    //        navigationSuiteItems = {
//    //            CoursesRoutes.entries.forEach { route ->
//    //                val value = route.key.value
//    //                item(
//    //                    selected = coursesKey == route.key,
//    //                    onClick = {
//    //                        if (backStack.last() != route.key) {
//    //                            backStack.add(route.key)
//    //                        }
//    //                    },
//    //                    icon = value.iconContent,
//    //                    label = value.labelContent,
//    //                    alwaysShowLabel = false,
//    //                )
//    //            }
//    //        },
//    //    ) {
//    //        when (coursesKey.value) {
//    //            CoursesMainKey -> CoursesMainScreen(modifier)
//    //            CoursesFavoritesKey -> CoursesFavoritesScreen(modifier)
//    //            CoursesAccountKey -> CoursesAccountScreen(modifier)
//    //        }
//    //    }
// }

@Composable
fun NavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color.Green),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CoursesRoutes.entries.forEach { route ->
            val entry = route.key.value

            IconButton(
                onClick = {},
                modifier =
                    Modifier
                        // .requiredHeight(200.dp)
                        .weight(1.0f / CoursesRoutes.entries.size),
            ) {
                entry.iconContent()
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
            }

//            Icon(
//                painter = painterResource(id = R.drawable.vk),
//                contentDescription = null,
//                modifier =
//                    Modifier
//                        .width(50.dp)
//                        .height(40.dp),
//            )
        }
    }
}
