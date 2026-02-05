package org.theycome.thousandcourses.presentation.courses.impl.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.theycome.thousandcourses.presentation.core.theme.ThousandCoursesTheme
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey
import org.theycome.thousandcourses.presentation.courses.impl.CoursesKeyData
import org.theycome.thousandcourses.presentation.courses.impl.CoursesRoute
import kotlin.collections.forEach

/**
 * Created by Ivan Yakushev on 05.02.2026
 */
@Preview
@Composable
fun NavigationBarPreview() {
    ThousandCoursesTheme {
        NavigationBar(
            CoursesKeyData.Main,
            CoursesRoute.routesOf(CoursesKey.Main, null),
        )
    }
}

@Composable
fun NavigationBar(
    keyData: CoursesKeyData,
    routes: List<CoursesRoute>,
    modifier: Modifier = Modifier,
) {
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
                bottomBarContent(route.selected, route.action, entryModifier)
            }
        }
    }
}
