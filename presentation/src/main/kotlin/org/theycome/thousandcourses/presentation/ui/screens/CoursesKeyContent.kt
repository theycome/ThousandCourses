package org.theycome.thousandcourses.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesKeyValue
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesRoutes
import org.theycome.thousandcourses.presentation.ui.screens.coursesKeyContent
import org.theycome.thousandcourses.presentation.ui.theme.ThousandCoursesTheme

/**
 * Created by Ivan Yakushev on 22.11.2025
 */
@Preview
@Composable
fun CoursesKeyContentPreview() {
    ThousandCoursesTheme {
        with(
            CoursesRoutes.entries
                .first()
                .key.value,
        ) {
            coursesKeyContent(Modifier)
        }
    }
}

val coursesKeyContent: @Composable CoursesKeyValue.(Modifier) -> Unit =
    { modifier ->
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier =
                    Modifier
                        .width(64.dp)
                        .height(32.dp)
                        .background(Color.Gray),
            ) {
                Box(
                    modifier =
                        Modifier
                            .align(Alignment.Center),
                ) {
                    Icon(
                        painter = painterResource(imageVectorId),
                        contentDescription = stringResource(labelId),
                        modifier = Modifier.fillMaxHeight(),
                    )
                }
            }
            Box(
                modifier =
                    Modifier
                        .height(16.dp)
                        .background(Color.Gray),
            ) {
                Text(
                    text = stringResource(labelId),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
