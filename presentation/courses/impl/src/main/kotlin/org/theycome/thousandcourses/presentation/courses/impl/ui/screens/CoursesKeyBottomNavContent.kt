package org.theycome.thousandcourses.presentation.courses.impl.ui.screens

import androidx.compose.foundation.background
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
import org.theycome.thousandcourses.presentation.courses.impl.CoursesKeyData
import org.theycome.thousandcourses.presentation.courses.impl.CoursesRoutes

/**
 * Created by Ivan Yakushev on 22.11.2025
 */
@Preview
@Composable
fun CoursesKeyBottomNavContentPreview() {
    ThousandCoursesTheme {
        with(
            CoursesRoutes.entries
                .first()
                .key,
        ) {
            coursesKeyBottomNavContent(Modifier)
        }
    }
}

val coursesKeyBottomNavContent: @Composable CoursesKeyData.(Modifier) -> Unit =
    { modifier ->
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
                        .background(MaterialTheme.colorScheme.surfaceVariant),
            ) {

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colorScheme.onSurfaceVariant,
                                shape = MaterialTheme.shapes.medium,
                            ),
                )
                Box(
                    modifier =
                        Modifier
                            .align(Alignment.Center),
                ) {
                    Icon(
                        painter = painterResource(imageVectorId),
                        contentDescription = stringResource(labelId),
                        tint = MaterialTheme.colorScheme.primary,
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
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
