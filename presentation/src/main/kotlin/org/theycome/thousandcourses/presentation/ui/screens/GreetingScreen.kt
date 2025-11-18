package org.theycome.thousandcourses.presentation.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import org.theycome.thousandcourses.presentation.R
import org.theycome.thousandcourses.presentation.ui.components.InputTextField
import org.theycome.thousandcourses.presentation.ui.components.validators.EmailValidator
import org.theycome.thousandcourses.presentation.ui.components.validators.NotEmptyTextValidator
import org.theycome.thousandcourses.presentation.ui.theme.SpecialColors
import org.theycome.thousandcourses.presentation.ui.theme.ThemeColors
import org.theycome.thousandcourses.presentation.ui.theme.ThousandCoursesTheme

/**
 * Created by Ivan Yakushev on 13.10.2025
 */
@Preview
@Composable
fun GreetingScreenPreview() =
    ThousandCoursesTheme {
        GreetingScreen({})
    }

@Composable
fun GreetingScreenTryFullDesign(
    onEnterKey: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val standardPadding = dimensionResource(R.dimen.standard_padding)
    var enterButtonEnabled by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Title(
            Modifier
                .padding(top = 140.dp, start = standardPadding, end = standardPadding)
                .height(36.dp),
        )
        Inputs(
            onInput = { payload ->
                enterButtonEnabled = payload != null
            },
            modifier =
                Modifier
                    .padding(top = 28.dp, start = standardPadding, end = standardPadding),
            autoValues = true,
        )
        EnterButton(
            onEnterKey = onEnterKey,
            enabled = enterButtonEnabled,
            modifier =
                Modifier
                    .padding(top = 24.dp, start = standardPadding, end = standardPadding)
                    .height(50.dp)
                    .fillMaxWidth(),
        )
        Actions(
            Modifier
                .padding(top = standardPadding)
                .fillMaxWidth(),
        )
        HorizontalDivider(
            Modifier
                .padding(top = 32.dp, start = standardPadding, end = standardPadding)
                .height(10.dp),
        )
        SocialMedia(
            Modifier
                .padding(top = 32.dp, start = standardPadding, end = standardPadding)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun GreetingScreen(
    onEnterKey: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val standardPadding = dimensionResource(R.dimen.standard_padding)
    var enterButtonEnabled by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Title(
            Modifier
                .padding(top = 140.dp, start = standardPadding, end = standardPadding)
                .height(36.dp),
        )
        Inputs(
            onInput = { payload ->
                enterButtonEnabled = payload != null
            },
            modifier =
                Modifier
                    .padding(top = 28.dp, start = standardPadding, end = standardPadding),
            autoValues = true,
        )
        EnterButton(
            onEnterKey = onEnterKey,
            enabled = enterButtonEnabled,
            modifier =
                Modifier
                    .padding(top = 24.dp, start = standardPadding, end = standardPadding)
                    .height(50.dp)
                    .fillMaxWidth(),
        )
        Actions(
            Modifier
                .padding(top = standardPadding)
                .fillMaxWidth(),
        )
        HorizontalDivider(
            Modifier
                .padding(top = 32.dp, start = standardPadding, end = standardPadding)
                .height(10.dp),
        )
        SocialMedia(
            Modifier
                .padding(top = 32.dp, start = standardPadding, end = standardPadding)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = stringResource(R.string.enter_caption),
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}

class InputsPayload(
    val email: String,
    val password: String,
)

/**
 * Compose code sometimes crashes with an inner error of `Missed recording an endGroup`
 * when handling passing values upstream from Inputs and transitioning from GreetingScreen
 * to other destinations in a back stack
 *
 * Probable causes of this bug could be:
 * - Heavy GreetingScreen UI structure
 * - Capturing remembered String? values to remove their nullability (use double bangs to circumvent this)
 * - Using wrapper class to pass values upstream
 *
 * Google issue tracker mentions early returns as a reason for the bug, we try to avoid it by placing upstream call
 * at the end of the composable.
 *
 * `Still, despite all the efforts, the crash WILL happen from time to time for reasons unknown..`
 */
@Composable
fun Inputs(
    onInput: (InputsPayload?) -> Unit,
    modifier: Modifier = Modifier,
    autoValues: Boolean = false,
) {
    var email: String? by remember { mutableStateOf(null) }
    var password: String? by remember { mutableStateOf(null) }
    val emailValidator by remember { mutableStateOf(EmailValidator()) }
    val notEmptyTextValidator by remember { mutableStateOf(NotEmptyTextValidator()) }

    /**
     * See function description
     */
    fun createPayloadUsingDoubleBangs(): InputsPayload? =
        if (email != null && password != null) {
            InputsPayload(email!!, password!!)
        } else {
            null
        }

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.email_caption),
            style = MaterialTheme.typography.titleMedium,
        )
        InputTextField(
            value = if (autoValues) "name@mail.ru" else "",
            placeholderId = R.string.email_placeholder,
            onInput = {
                email = it
            },
            modifier =
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            validator = emailValidator,
            visualTransformation = emailValidator,
        )
        Text(
            text = stringResource(R.string.password_caption),
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        InputTextField(
            value = if (autoValues) "password" else "",
            placeholderId = R.string.password_placeholder,
            onInput = {
                password = it
            },
            modifier =
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
            validator = notEmptyTextValidator,
            visualTransformation = notEmptyTextValidator,
        )
    }

    // See function description
    val payload = createPayloadUsingDoubleBangs()
    onInput(payload)
}

@Composable
fun EnterButton(
    onEnterKey: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onEnterKey,
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(
            text = stringResource(R.string.enter_caption),
        )
    }
}

@Composable
fun Actions(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Text(stringResource(R.string.noaccount_caption))
            Spacer(Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.registration_caption),
                color = ThemeColors.Green,
            )
        }
        Text(
            text = stringResource(R.string.forgotpass_caption),
            color = ThemeColors.Green,
        )
    }
}

@Composable
fun SocialMedia(modifier: Modifier = Modifier) {
    val okGradientBrush =
        Brush.verticalGradient(
            colors = listOf(SpecialColors.OK1, SpecialColors.OK2),
        )
    val context = LocalContext.current

    Row(modifier = modifier) {
        Button(
            onClick = {
                context.startActivity(
                    Intent(Intent.ACTION_VIEW, "https://www.vk.com".toUri()),
                )
            },
            modifier =
                Modifier
                    .weight(0.5f),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = SpecialColors.VK,
                ),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.vk),
                contentDescription = null,
                modifier =
                    Modifier
                        .width(50.dp)
                        .height(40.dp),
            )
        }
        Spacer(Modifier.width(16.dp))
        Button(
            onClick = {
                context.startActivity(
                    Intent(Intent.ACTION_VIEW, "https://www.ok.ru".toUri()),
                )
            },
            modifier =
                Modifier
                    .weight(0.5f)
                    .clip(RoundedCornerShape(30.dp))
                    .background(okGradientBrush),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ok),
                contentDescription = null,
                modifier =
                    Modifier
                        .width(50.dp)
                        .height(40.dp),
            )
        }
    }
}
