package org.theycome.thousandcourses.presentation.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ThemeColors {
    val Dark = Color(0xFF151515)
    val LightGray = Color(0xFF32333A)
    val White = Color(0xFFF2F2F3)
    val Green = Color(0xFF12B956)
    val SurfaceVariant = Color(0xFF24252A)
    val OnSurfaceVariant = Color(0xFF32333A)
    val OnSurface = Color(0xFF929296)
    val Stroke = Color(0xFF4D555E)

    val DarkGray = Color(0xFF6534FF)
    val Caption = Color(0xFF6534FF)
}

object SpecialColors {
    val VK = Color(0xFF2683ED)
    val OK1 = Color(0xFFF98509)
    val OK2 = Color(0xFFF95D00)
}

private val DarkColorScheme =
    darkColorScheme(
        primary = ThemeColors.Green,
        onPrimary = ThemeColors.White,
//        primaryContainer = ,
//        onPrimaryContainer = ,
//        inversePrimary = ,
//        secondary = ,
//        onSecondary = ,
//        secondaryContainer = ,
//        onSecondaryContainer = ,
//        tertiary = ,
//        onTertiary = ,
//        tertiaryContainer = ,
//        onTertiaryContainer = ,
        background = ThemeColors.Dark,
//        onBackground = ,
//        surface = ,
        onSurface = ThemeColors.OnSurface,
        surfaceVariant = ThemeColors.SurfaceVariant,
        onSurfaceVariant = ThemeColors.OnSurfaceVariant,
//        surfaceTint = ,
//        inverseSurface = ,
//        inverseOnSurface = ,
//        error = ,
//        onError = ,
//        errorContainer = ,
//        onErrorContainer = ,
//        outline = ,
        outlineVariant = ThemeColors.Stroke,
//        scrim = ,
//        surfaceBright = ,
//        surfaceContainer = ,
//        surfaceContainerHigh = ,
        surfaceContainerHighest = ThemeColors.LightGray,
//        surfaceContainerLow = ,
//        surfaceContainerLowest = ,
//        surfaceDim =
    )

@Composable
fun ThousandCoursesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content,
    )
}
