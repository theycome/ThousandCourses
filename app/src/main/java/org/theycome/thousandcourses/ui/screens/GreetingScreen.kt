package org.theycome.thousandcourses.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.theycome.thousandcourses.R
import org.theycome.thousandcourses.ui.components.InputTextField

/**
 * Created by Ivan Yakushev on 13.10.2025
 */
@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    val standardPadding = dimensionResource(R.dimen.standard_padding)

    Column {
        Title(standardPadding)
        Inputs(standardPadding)
//        Button({}) {
//            Text(
//                text = "Вход",
//            )
//        }
//        HorizontalDivider(Modifier.height(10.dp))
    }
}

@Composable
private fun Title(standardPadding: Dp) {
    Box(
        modifier =
            Modifier
                .padding(top = 140.dp, start = standardPadding, end = standardPadding)
                .height(36.dp),
    ) {
        Text(
            text = stringResource(R.string.enter_caption),
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}

@Composable
private fun Inputs(standardPadding: Dp) {
    Column(
        modifier =
            Modifier
                .padding(top = 28.dp, start = standardPadding, end = standardPadding),
    ) {
        Text(
            text = stringResource(R.string.email_caption),
            style = MaterialTheme.typography.titleMedium,
        )
        InputTextField(
            value = "",
            modifier =
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            placeholderId = R.string.email_placeholder,
        )
        Text(
            text = stringResource(R.string.password_caption),
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        InputTextField(
            value = "",
            modifier =
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            placeholderId = R.string.password_placeholder,
        )
    }
}
