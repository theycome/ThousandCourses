package org.theycome.thousandcourses.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.theycome.thousandcourses.R

/**
 * Created by Ivan Yakushev on 13.10.2025
 */
@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    Column {
        Box(
            modifier =
                Modifier
                    .padding(top = 140.dp, start = 16.dp, end = 16.dp)
                    .height(36.dp),
        ) {
            Text(
                text = stringResource(R.string.enter_caption),
                style = MaterialTheme.typography.headlineLarge,
            )
        }
//        TextField("bla", {})
//        Button({}) {
//            Text(
//                text = "Вход",
//            )
//        }
//        HorizontalDivider(Modifier.height(10.dp))
    }
}
