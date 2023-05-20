package com.dev_akash.freedictionary.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.dev_akash.freedictionary.utils.AppUtils
import com.dev_akash.freedictionary.theme.resources.AppColors
import com.dev_akash.freedictionary.theme.resources.AppDimensions
import com.dev_akash.freedictionary.theme.resources.AppTypography
import com.dev_akash.freedictionary.theme.resources.LocalColors
import com.dev_akash.freedictionary.theme.resources.LocalDimensions
import com.dev_akash.freedictionary.theme.resources.LocalTypography
import com.dev_akash.freedictionary.theme.resources.colorDarkBackground
import com.dev_akash.freedictionary.theme.resources.colorDarkError
import com.dev_akash.freedictionary.theme.resources.colorDarkPrimary
import com.dev_akash.freedictionary.theme.resources.colorDarkTextPrimary
import com.dev_akash.freedictionary.theme.resources.colorDarkTextSecondary
import com.dev_akash.freedictionary.theme.resources.colorLightBackground
import com.dev_akash.freedictionary.theme.resources.colorLightError
import com.dev_akash.freedictionary.theme.resources.colorLightPrimary
import com.dev_akash.freedictionary.theme.resources.colorLightTextPrimary
import com.dev_akash.freedictionary.theme.resources.colorLightTextSecondary

object AppTheme {
    val colors: AppColors
        @Composable @ReadOnlyComposable get() = LocalColors.current

    val typography: AppTypography
        @Composable @ReadOnlyComposable get() = LocalTypography.current

    val dimensions: AppDimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current

}

fun lightColorsPalette(
    primary: Color = colorLightPrimary,
    secondary: Color = colorLightTextSecondary,
    textPrimary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    error: Color = colorLightError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = true
)


fun darkColorsPalette(
    primary: Color = colorDarkPrimary,
    secondary: Color = colorDarkTextSecondary,
    textPrimary: Color = colorDarkTextPrimary,
    textSecondary: Color = colorDarkTextSecondary,
    background: Color = colorDarkBackground,
    error: Color = colorDarkError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = false
)

@Composable
fun FreeDictionaryTheme(
    content: @Composable () -> Unit
) {
    val colors = if (AppUtils.isDarkMode) darkColorsPalette() else lightColorsPalette()

    CustomAppTheme(
        colors = colors,
        content = content
    )
}

@Composable
fun CustomAppTheme(
    colors: AppColors = AppTheme.colors,
    typography: AppTypography = AppTheme.typography,
    dimensions: AppDimensions = AppTheme.dimensions,
    content: @Composable () -> Unit
) {
    // creating a new object for colors to not mutate the initial colors set when updating the values
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography
    ) {
        content()
    }
}
