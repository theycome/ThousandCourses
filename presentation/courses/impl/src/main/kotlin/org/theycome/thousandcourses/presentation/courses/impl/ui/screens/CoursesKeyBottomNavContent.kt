package org.theycome.thousandcourses.presentation.courses.impl.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.theycome.thousandcourses.presentation.core.theme.ThousandCoursesTheme
import org.theycome.thousandcourses.presentation.courses.api.CoursesKey
import org.theycome.thousandcourses.presentation.courses.impl.CoursesKeyData
import org.theycome.thousandcourses.presentation.courses.impl.CoursesRoute

/**
 * Created by Ivan Yakushev on 22.11.2025
 */
@Preview
@Composable
fun CoursesKeyBottomNavContentPreviewSelected() {
    ThousandCoursesTheme {
        with(
            CoursesRoute
                .routesOf(selectedKey = CoursesKey.Main, navigator = null)
                .first()
                .keyData,
        ) {
            coursesKeyBottomNavContent(true, {}, Modifier)
        }
    }
}

@Preview
@Composable
fun CoursesKeyBottomNavContentPreviewUnselected() {
    ThousandCoursesTheme {
        with(
            CoursesRoute
                .routesOf(selectedKey = CoursesKey.Main, navigator = null)
                .first()
                .keyData,
        ) {
            coursesKeyBottomNavContent(false, {}, Modifier)
        }
    }
}

val coursesKeyBottomNavContent: @Composable CoursesKeyData.(selected: Boolean, action: () -> Unit, Modifier) -> Unit =
    { selected: Boolean, action: () -> Unit, modifier: Modifier ->
        Column(
            modifier =
                modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier =
                    Modifier
                        .width(64.dp)
                        .height(32.dp)
                        .clickable { action.invoke() }
                        .background(MaterialTheme.colorScheme.surfaceVariant),
            ) {
                if (selected) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .background(
                                    MaterialTheme.colorScheme.onSurfaceVariant,
                                    shape = MaterialTheme.shapes.medium,
                                ),
                    )
                }
                Box(
                    modifier =
                        Modifier
                            .align(Alignment.Center),
                ) {
                    Icon(
                        painter = painterResource(imageVectorId),
                        contentDescription = stringResource(labelId),
                        tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
            Box(
                modifier =
                    Modifier
                        .height(16.dp),
            ) {
                Text(
                    text = stringResource(labelId),
                    color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
